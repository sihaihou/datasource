package com.reyco.order.core.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 通过实现 HandlerExceptionResolver 接口做全局异常处理
 * @author reyco
 */
@Configuration
public class GlobalDefultExceptionHandler  implements HandlerExceptionResolver{
	
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ex.printStackTrace();
		ModelAndView mv = new ModelAndView();
		mv.addObject("error",ex.toString());
		//判断不同异常类型，做不同视图跳转
		if(ex instanceof NullPointerException) {
			mv.setViewName("/error/error1.html");
		}else if(ex instanceof ArithmeticException) {
			mv.setViewName("/error/error2.html");
		}
		return mv;
	}
		
}
