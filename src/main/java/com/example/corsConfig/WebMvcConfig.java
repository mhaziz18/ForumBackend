package com.example.corsConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig /*implements WebMvcConfigurer*/ {
	/*
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedMethods("/**")
		.allowedOrigins("http://127.0.0.1:4200")
		.allowedHeaders("*")
		.allowCredentials(false)
		.maxAge(-1);
	}*/

}
