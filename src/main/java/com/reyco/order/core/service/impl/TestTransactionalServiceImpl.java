package com.reyco.order.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.reyco.order.core.dao.TestADao;
import com.reyco.order.core.dao.TestBDao;
import com.reyco.order.core.domain.TestA;
import com.reyco.order.core.domain.TestB;
import com.reyco.order.core.service.TestTransactionalService;

/**
 * @author reyco
 * @date 2021年3月10日---上午10:27:45
 * 
 *       <pre>
 *
 *       <pre>
 */
@Service
public class TestTransactionalServiceImpl implements TestTransactionalService {

	@Autowired
	private TestADao testADao;
	@Autowired
	private TestBDao testBDao;
	@Autowired
	TestTransactionalServiceImpl testTransactionalServiceImpl;

	@Transactional(propagation = Propagation.NESTED)
	@Override
	public void test1() {
		TestA testA = new TestA();
		testA.setName("zs1");
		testA.setDesc("备注1");
		testADao.insert(testA);
		testTransactionalServiceImpl.test2();
	}

	@Transactional(propagation = Propagation.NESTED)
	@Override
	public void test2() {
		TestB testb = new TestB();
		testb.setName("zs1");
		testb.setDesc("备注1");
		testBDao.insert(testb);
		throw new RuntimeException("异常");
	}
}
