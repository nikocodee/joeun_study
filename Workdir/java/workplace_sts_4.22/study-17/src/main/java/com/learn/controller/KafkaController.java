package com.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.service.KafkaProducerService;

@RestController
public class KafkaController {
	@Autowired
	private KafkaProducerService kafkaProducerService;
	
	//@RequestBody JSON 객체로 받음
	@PostMapping("/order")
	public String send(@RequestBody String message) {
		String result = null; // "OK" + message;
		result = kafkaProducerService.send("order", message);
		result = result + " : " + message;
		return result;
	}
}
