package com.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learn.service.CacheService;

@RestController
public class CacheController {
	@Autowired
	private CacheService cacheService;
	
	@GetMapping("/data/{id}")
	public String getData(@PathVariable String id) {
		return cacheService.getDataById(id);
	}
}
