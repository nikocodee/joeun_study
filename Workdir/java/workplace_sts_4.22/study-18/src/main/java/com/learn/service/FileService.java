package com.learn.service;

import java.io.IOException;
import java.io.InputStream;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.client.gridfs.GridFSBucket;

@Service
public class FileService {
	@Autowired
	private GridFSBucket gridFSBucket;
	
	public String uploadFile(MultipartFile file) throws IOException {
		// try문 stream을 자동으로 닫아주는 역할
		try(InputStream input = file.getInputStream()){
			ObjectId fileId = gridFSBucket.uploadFromStream(file.getOriginalFilename(), input);
			String result = fileId.toHexString();
			return result;
		}
		
//		String result = null;
//		try {
//			InputStream input = file.getInputStream();
//			ObjectId fileId = gridFSBucket.uploadFromStream(file.getOriginalFilename(), input);
//			result = fileId.toHexString();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
////			e.printStackTrace();
//			result = "-";
//		}
//		return result;
	}
}
