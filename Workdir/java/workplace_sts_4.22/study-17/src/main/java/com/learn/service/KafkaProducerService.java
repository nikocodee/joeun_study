package com.learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
//	private final KafkaTemplate<String, String> kafkaTemplate;
//	
//	public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
	
	//throws Exception 남이 호출할거다
	//try-exception 내가 처리할거다
	//throw new Exception 에러 발생시킴
	public String send(String topic, String msg){
		String result = null;
		try {
			kafkaTemplate.send(topic, msg);
			kafkaTemplate.flush();
			result = "Y";
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			result = "N";
		}
		return result;
	}
}
