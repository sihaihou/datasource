package com.reyco.order.core.datasource.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.reyco.order.core.service.TestTransactionalService;

/**
*@author reyco
*@date  2021年5月21日---下午3:11:42
*<pre>
*
*<pre> 
*/
@SuppressWarnings("all")
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTrans {
	
	@Autowired
	TestTransactionalService testTransactionalService;
	
	@Test
	public void test() throws Exception {
		testTransactionalService.test1();
	}
}
