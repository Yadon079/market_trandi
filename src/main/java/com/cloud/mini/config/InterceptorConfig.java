package com.cloud.mini.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cloud.mini.member.interceptor.SignInCheckInterceptor;
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	@Autowired
	private SignInCheckInterceptor signInCheckInterceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(signInCheckInterceptor).addPathPatterns("/board/write");
		registry.addInterceptor(signInCheckInterceptor).addPathPatterns("/event/writeE");
		registry.addInterceptor(signInCheckInterceptor).addPathPatterns("/complain/writeC");
		registry.addInterceptor(signInCheckInterceptor).addPathPatterns("/question/writeQ");
		registry.addInterceptor(signInCheckInterceptor).addPathPatterns("/list");
		registry.addInterceptor(signInCheckInterceptor).addPathPatterns("/owner");
		registry.addInterceptor(signInCheckInterceptor).addPathPatterns("/update");
		WebMvcConfigurer.super.addInterceptors(registry);
	}
}