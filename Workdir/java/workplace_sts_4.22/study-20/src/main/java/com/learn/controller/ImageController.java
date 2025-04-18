package com.learn.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.learn.service.ImageService;
import com.mongodb.client.gridfs.model.GridFSFile;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/images")
public class ImageController {
	
	@Autowired
	private GridFsOperations gridFsOperations;
	
	@Autowired
	private ImageService imageService;
	
	@PostMapping("/upload")
	public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file){
		Map<String, String> result = new HashMap<>();
		String imageUrl = imageService.uploadImage(file);
		if("-".equals(imageUrl)) {
			return ResponseEntity.status(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR).build();
		}else {
			result.put("fileId", imageUrl);
		}
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/{id}")
	public void getImage(@PathVariable String id, HttpServletResponse response){
		GridFSFile file = imageService.getImage(id);
		
		if(file==null) {
			response.setStatus(HttpStatus.SC_NOT_FOUND);
			return;
		}
		
		try (InputStream inputStream = gridFsOperations.getResource(file).getInputStream()){
			response.setContentType(file.getMetadata().getString("_contentType"));
			StreamUtils.copy(inputStream, response.getOutputStream());
		} catch (Exception e) {
//			e.printStackTrace();
			response.setStatus(HttpStatus.SC_NOT_FOUND);
			return;
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> deleteImage(@PathVariable String id){
		Map<String, String> result = new HashMap<>();
		String res = imageService.deleteImage(id);
		if(("-".equals(res)) || (res==null)) {
			return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).build();
		}else {
			result.put("id", id);
			result.put("Delete", res);
		}
		return ResponseEntity.ok(result);
	}
}
