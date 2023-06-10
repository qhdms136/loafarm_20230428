package com.loafarm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.loafarm.common.FileManagerService;
import com.loafarm.interceptor.PermissionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Autowired
	private PermissionInterceptor interceptor;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/images/**") // 웹 이미지 주소 cf) http://localhost/images/aaaa_2023050601/good.png

		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH); // window / 3개 mac / 2개

	}
	
	// intercepter 설정 추가
	public void addInterceptors(InterceptorRegistry registry) {
		registry
		.addInterceptor(interceptor) // 등록할 인터셉터 설정
		.addPathPatterns("/**")	// 적용할 URL 패턴 설정
		.excludePathPatterns("/favicon.ico", "/error", "/static/**", "/user/sign_out");	// 인터셉터를 제외할 url 패턴 등록
	}
}
