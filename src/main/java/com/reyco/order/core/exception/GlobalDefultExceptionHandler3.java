package com.reyco.order.core.exception;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * 通过 SimpleMappingExceptionResolver 做全局异常处理
 * @author reyco
 */
//@Configuration
public class GlobalDefultExceptionHandler3 {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 该方法必须要有返回值。返回值类型必须是： SimpleMappingExceptionResolver
	 * @return
	 */
	@Bean
	public SimpleMappingExceptionResolver getSimpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
		
		/**
		 * 参数一：异常的类型，注意必须是异常类型的全名 * 
		 * 参数二：视图名称
		 */
		Properties mappings = new Properties();
		mappings.put("java.lang.NullPointerException", "/error/error1.html");
		mappings.put("java.lang.ArithmeticException", "/error/error2.html");
		
		resolver.setExceptionMappings(mappings);
		return resolver;
	}
	
}
