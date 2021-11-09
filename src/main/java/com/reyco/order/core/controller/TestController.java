package com.reyco.order.core.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reyco.order.core.domain.TestA;

/**
*@author reyco
*@date  2021年5月20日---下午9:37:29
*<pre>
*
*<pre> 
*/
@RestController
@RequestMapping("test")
public class TestController {
	
	@GetMapping("test1")
	public Object test(TestA testa) {
		System.out.println("testa:"+testa);
		int a = 1/0;
		return testa;
	}
	
}
