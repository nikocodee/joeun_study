package com.learn.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final Path uploadPath = Path.of(System.getProperty("user.dir"), "uploads");

    // 디렉터리 생성
    public UserController() throws Exception {
        Files.createDirectories(uploadPath);
    }

    // ✅ 1. GET 방식 : Query String 처리
    // URL: http://localhost:8080/user/query?name=John&age=25
    @GetMapping("/query")
    public String getQueryString(@RequestParam String name, @RequestParam int age) {
        return "QueryString 처리: name=" + name + ", age=" + age;
    }

    // ✅ 2. POST 방식 : Path Variable + Request Body 처리 (JSON)
    @PostMapping("/path/{name}/{age}")
    public String postPathAndBody(@PathVariable String name, @PathVariable int age,
                                  @RequestBody UserRequest userRequest) {
        // Path로 받은 name, age와 Body로 받은 UserRequest(name, age)를 함께 처리
        return String.format("Path: %s(%d), RequestBody: %s/%d",
                name, age, userRequest.getName(), userRequest.getAge());
    }

    // ✅ 3. POST 방식 : HTML <form> 태그 방식
    @PostMapping("/form")
    public String postForm(UserRequest userRequest) {
        return "Form 방식 : name=" + userRequest.getName() + ", age=" + userRequest.getAge();
    }

    // ✅ 4. POST 방식 : 첨부파일 업로드 (multipart/form-data)
    @PostMapping("/upload")
    public String postMultipart(@RequestParam("name") String name,
                                @RequestParam("age") int age,
                                @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return "업로드된 파일이 없습니다.";
        }

        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path target = uploadPath.resolve(fileName);
            file.transferTo(target);

            return "파일 업로드 성공: name=" + name + ", age=" + age +
                   ", 저장경로=" + target.toAbsolutePath();

        } catch (Exception e) {
            return "파일 업로드 실패: " + e.getMessage();
        }
    }
    
    // ✅ 5. POST 방식 : 멀티 첨부파일 업로드 (multipart/form-data)
    @PostMapping(value="/uploads")
    public ResponseEntity<String> postUserWithFile(MultipartHttpServletRequest req) {
//        MultipartFile file = req.getFiles("file").get(0);
//        log.info("파일 업로드 방식: name={}, age={}, file={}", file.getOriginalFilename());
        // 입력된 파일목록
        List<MultipartFile> files = new ArrayList<>();
        // 파일 저장
        for(int i=0; i<req.getFiles("file").size(); i++) {
            files.add(req.getFiles("file").get(i));
        }
        
        // 파일 추출 테스트
        for(int i=0; i<req.getFiles("file").size(); i++) {
            log.info("req.getParameter(\"name\") : " + req.getParameter("name"));
            log.info("req.getFile(\"file\") : " + req.getFile("file"));
            log.info("req.getFile(\"file\").getOriginalFilename() : " + req.getFiles("file").get(i).getOriginalFilename());      // 실제 파일이름 출력
            try {
				log.info("req.getFiles(\"file\").get("+i+").getBytes() : " + req.getFiles("file").get(i).getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}                 // 파일의 용량 출력
            log.info("req.getFiles(\"file\").get("+i+").getName() : " + req.getFiles("file").get(i).getName());                  // name 속성값 출력
            log.info("req.getFiles(\"file\").size() : " + req.getFiles("file").size());                            // 입력된 파일의 개수 출력
        }
        
        // 첨부된 파일(MultipartFile)을 처리할 수 있습니다.
        if (files != null && files.size() > 0) {
            for (MultipartFile file : files) {
                // 파일 처리 로직 시작
                String randomUUID = UUID.randomUUID().toString();  // 파일 이름 중복 방지를 위한 랜덤 UUID 생성
                String OriginalFilename = file.getOriginalFilename();  // 실제 파일 이름
                String Extension = OriginalFilename.substring(OriginalFilename.lastIndexOf("."));  // 파일 확장자 추출
                String saveFileName = randomUUID + Extension;  // 저장할 파일 이름 생성
                
                // 파일 저장
                Path target = uploadPath.resolve(saveFileName);
                try {
                    file.transferTo(target);
                } catch (IllegalStateException | IOException e) {
                    e.printStackTrace();
                    // 예외 처리
                }
            }
        }
    	  
    	
        // 파일 처리 로직 추가 가능
        String fileInfo = String.format("Uploaded file: %s (%d bytes)", 
        		req.getFiles("file").get(0).getOriginalFilename(), req.getFiles("file").get(0).getSize());

        return ResponseEntity.ok("파일 업로드 방식: name=" + req.getParameter("name") + ", age=" + req.getParameter("age") + ", " + fileInfo);
    }


    // ✅ 6. 파일 다운로드 처리
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName) throws Exception {
        Path file = uploadPath.resolve(fileName);
        if (!Files.exists(file)) {
            return ResponseEntity.notFound().build();
        }

        String contentType = Files.probeContentType(file);
        contentType = contentType != null ? contentType : "application/octet-stream";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(Files.readAllBytes(file));
    }

    // Request 객체로 데이터를 받기 위한 DTO 클래스
    static class UserRequest {
        private String name;
        private int age;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
    }
}