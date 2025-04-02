//package com.learn.service;
//
//import java.nio.charset.StandardCharsets;
//
//
//import org.springframework.data.redis.connection.Message;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RedisSubscriber {
//    public void onMessage(Message message, byte[] pattern) {
//        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
//        System.out.println("📥 Received message: "+ msg);
//    }
// 
//}