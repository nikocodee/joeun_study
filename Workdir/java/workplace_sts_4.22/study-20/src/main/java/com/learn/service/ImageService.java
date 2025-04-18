package com.learn.service;

import java.io.IOException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.client.gridfs.model.GridFSFile;

@Service
public class ImageService {
	@Autowired
	private GridFsTemplate gridFsTemplate;
	
	public String uploadImage(MultipartFile file){
		String result = null;
		ObjectId fileId = null;
		try {
			fileId = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType());
			result = "/api/images/"+fileId.toHexString();
		} catch (IOException e) {
//			e.printStackTrace();
//			return ResponseEntity.status(500).body("File Not Transfered");
			result = "-";
		}
		return result;
	}
	
	public GridFSFile getImage(String id){
		GridFSFile result = null;
		result = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(id)));
//		result = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
		return result;
	}
	
	public String deleteImage(String id){
		String result = null;
		
		try {
			gridFsTemplate.delete(Query.query(Criteria.where("_id").is(id)));
			result = "Y";
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			result = "-";
		}
		return result;
	}
}
