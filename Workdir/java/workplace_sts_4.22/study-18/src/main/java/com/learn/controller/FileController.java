package com.learn.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.learn.service.FileService;

@RestController
public class FileController {
	@Autowired
	private FileService fileService;
	
	@PostMapping("/upload")
	public Map<String, Object> uploadFile(@RequestParam MultipartFile file){
		Map<String, Object> result = new HashMap<>();
		String fileId = null;
		try {
			fileId = fileService.uploadFile(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			fileId = "-";
		}
		result.put("fileId", fileId);
		result.put("fileName", file.getOriginalFilename());
		return result;
	}
}
