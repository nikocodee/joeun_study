package com.learn.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
	//private 서비스 로직을 외부에서 보면 안됨
	private void simulateSlowService(){
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Cacheable(value = "data", key = "#id")
	public String getDataById(String id) {
		String result = "getDataById Call : " + id; 
		simulateSlowService();
		return result;
	}
}
