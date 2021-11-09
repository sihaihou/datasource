package com.reyco.order.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reyco.order.core.service.TestTransactionalService;
import com.reyco.order.core.utils.R;

/**
*@author reyco
*@date  2021年5月19日---下午4:41:18
*<pre>
*
*<pre> 
*/
@RestController
@RequestMapping("trans")
public class TestTransactionalController {
	@Autowired
	private TestTransactionalService testTransactionalService;
	
	@GetMapping("test1")
	public Object test1() {
		testTransactionalService.test1();
		return R.success("Ok");
	}
}
