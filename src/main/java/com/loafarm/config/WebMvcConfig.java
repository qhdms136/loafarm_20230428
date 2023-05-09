package com.loafarm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.loafarm.common.FileManagerService;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/images/**") // 웹 이미지 주소 cf) http://localhost/images/aaaa_2023050601/good.png
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH); // window / 3개 mac / 2개
	}
}
