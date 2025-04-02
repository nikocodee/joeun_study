//package com.learn.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.learn.service.RedisPublisher;
//
//@RestController
//public class MessageController {
//	@Autowired
//	private RedisPublisher redisPublisher;
//
//	@GetMapping("/pub")
//	public String sendMessage(@RequestParam String msg) {
//		String result = "Message" + msg;
//		redisPublisher.publish(msg);
//		return result;
//	}
//}
//
