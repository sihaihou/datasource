package com.reyco.order.core.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理
 * @author reyco
 *
 */
//@ControllerAdvice
public class GlobalDefultExceptionHandler2 {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 声明要捕获的异常
	 * @param reuqest
	 * @param response
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView defultExcepitonHandler(HttpServletRequest reuqest,HttpServletResponse response,Exception ex) {
		ex.printStackTrace();
		ModelAndView mv = new ModelAndView();
		mv.addObject("error",ex.toString());
		mv.setViewName("/error/error1.html");
		return mv;
	}
	/**
	 * 声明要捕获的异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ArithmeticException.class)
	public ModelAndView ArithmeticException(Exception e) {
		ModelAndView model = new ModelAndView();
		model.addObject("error",e.toString());
		model.setViewName("/error/error2.html");
		return model;
	}
}
