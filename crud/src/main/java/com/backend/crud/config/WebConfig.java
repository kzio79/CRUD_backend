package com.backend.crud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	private final long MAX_AGE_SECS = 3000;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		registry.addMapping("/**")
		.allowedOrigins("http://localhost:3000",
				"http://127.0.0.1:3000",
				"http://192.168.137.1:3000")
		.allowedMethods("GET","POST","PUT","DELETE")
		.allowedHeaders("*")
		.allowCredentials(true)
		.maxAge(MAX_AGE_SECS);
	}
}
