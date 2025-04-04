package com.learn.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import jakarta.servlet.MultipartConfigElement;

@Configuration
public class MultipartConfig {
    // spring boot 3 이상부터 multipart를 사용할때 생성해줘야 한다.
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation("C:/uploads"); // 파일 저장할 임시 위치 지정 // 상대경로 설정 가능 (프로젝트 루트) factory.setLocation("uploads");
        factory.setMaxRequestSize(DataSize.ofMegabytes(100L));
        factory.setMaxFileSize(DataSize.ofMegabytes(100L));

        return factory.createMultipartConfig();
    }	
}
