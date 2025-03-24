package com.learn.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/board")
public class BoardController {

	 private final Path uploadPath = Path.of(System.getProperty("user.dir"), "uploads");
	 
	// get방식은 query string으로 받음
	@GetMapping
	public Map<String, Object> getBoards(@RequestParam Map<String, Object> params) {
		log.info("*** getBoards Call! ***");
		Map<String, Object> result = new HashMap<>();
		List<Map<String, Object>> content = new ArrayList<>();

		Map<String, Object> map1 = new HashMap<>();
//		map1.put("content", params.get("searchKeyWord"));
//		map1.put("writer", params.get("name"));
//		map1.put("title", params.get("title"));
		map1.put("content", "test Content1 ");
		map1.put("writer", "test Writer1");
		map1.put("title", "test Title1");
		map1.put("id", 1);

		Map<String, Object> map2 = new HashMap<>();
		map2.put("content", "test Content2 ");
		map2.put("writer", "test Writer2");
		map2.put("title", "test Title2");
		map2.put("id", 2);

		content.add(map1);
		content.add(map2);

		result.put("content", content);
		result.put("totalPages", 10);

		return result;
	}

	@GetMapping("/{id}")
	public Map<String, Object> getBoardById(@PathVariable long id) {
		Map<String, Object> result = new HashMap<>();
		result.put("content", "content text");
		result.put("writer", "writer kim");
		result.put("title", "test title " + id);
		result.put("createdAt", "2025/05/05");
		result.put("views", 13);
		result.put("id", id);
		return result;
	}

	@PostMapping
	public Map<String, Object> createBoard(MultipartHttpServletRequest req) {
		Map<String, Object> result = new HashMap<>();
		List<MultipartFile> files = new ArrayList<>();
		for (int i = 0; i < req.getFiles("file").size(); i++) {
			files.add(req.getFiles("file").get(i));
		}
		boolean cond1 = files != null;
		boolean cond2 = files.size() > 0;
		if (cond1 && cond2) {
			//file은 원소, files는 list
			for(MultipartFile file : files) {
				String randomUUID = UUID.randomUUID().toString();
				String originalFileName = file.getOriginalFilename();
				result.put(originalFileName, randomUUID);
				String Extension = originalFileName.substring(originalFileName.lastIndexOf("."));
				log.debug(randomUUID, "|", originalFileName, "|", Extension);
				String saveFileName = randomUUID + Extension;
				
				Path target = uploadPath.resolve(saveFileName);
				try {
					//target에 파일 업로드
					file.transferTo(target);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace(); //운영때는 하면 안됨
					log.error("createBoard IllegalStateException raise!!!"+e.getMessage());
				} catch (IOException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
					log.error("createBoard IOException raise!!!"+e.getMessage());
				} catch (Exception e) {
//					e.printStackTrace();
					log.error("createBoard Exception raise!!!"+e.getMessage());
				}
			}
		}
			return result;
	}
}

//@RestController
//@RequestMapping("/board")
//public class BoardController {
//   
//   @GetMapping
//   public Map<String,Object> getBoards(@RequestParam Map<String, Object> params) {
//      Map<String,Object> result = new HashMap();
//      List<Map<String, Object>> content = new ArrayList<>();
//      
//      Map<String, Object> map1 = new HashMap<>();
//      try {
//         map1.put("content", Integer.valueOf((String) params.get("searchKeyWord")));
//      } catch (Exception e) {
//         map1.put("content", (String) params.get("searchKeyWord"));
//      }
//      map1.put("content", "test content1");
//      map1.put("writer","test Writer1");
//      map1.put("title","test Title1");
//      map1.put("id",1);
//      
//      Map<String, Object> map2 = new HashMap<>();
//      map2.put("content", "test Writer2");
//      map2.put("writer", "test Writer2");
//      map2.put("title", "test Title2");
//      map2.put("id", 2);
//      
//      content.add(map1);
//      content.add(map2);
//      
//      result.put("content", content);
//      result.put("totalPages", 100);
//      
//      return result;
//   }
//   
//   @GetMapping("/{id}")
//   public Map<String, Object> getBoardById(@PathVariable Long id) {
//      Map<String, Object> result = new HashMap<>();
//      result.put("content", "content text");
//      result.put("writer", "writer kjh");
//      result.put("title", "test title"+id);
//      result.put("createdAt", "2025/05/05"+id);
//      result.put("views",13);
//      result.put("id", id);
//      return result;
//   }
//}
