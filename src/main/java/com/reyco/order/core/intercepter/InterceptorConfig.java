package com.reyco.order.core.intercepter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	
	@Autowired
	private SecurityIntercepter securityIntercepter;
	
	/**
	 * 拦截器的执行顺序和配置顺序有关系，即先配置顺序就在前
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 注册拦截器
		registry.addInterceptor(securityIntercepter)
		// 拦截的请求路径
		.addPathPatterns("/api/**")
		// 无需拦截的请求
		.excludePathPatterns("/static/**");
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/","classpath:/resources/","classpath:/templates/");
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
}