package com.learn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.client.MongoClients;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;

@Configuration
public class MongoConfig {
	
	@Bean
	public GridFSBucket gridBucket() {
		return GridFSBuckets.create(MongoClients.create("mongodb://localhost:27017").getDatabase("filedb"));
	}
}
