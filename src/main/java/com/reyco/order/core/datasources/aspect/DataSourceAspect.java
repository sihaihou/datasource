package com.reyco.order.core.datasources.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.reyco.order.core.datasources.annotation.DataSource;
import com.reyco.order.core.datasources.config.DataSourceNames;
import com.reyco.order.core.datasources.config.DynamicDataSource;

/**
 * 多数据源，切面处理类
 */
@Aspect
@Component
public class DataSourceAspect /*implements Ordered */{
	
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Pointcut("@annotation(com.reyco.order.core.datasources.annotation.DataSource)")
	public void dataSourcePointCut() {

	}

	@Around("dataSourcePointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		try {
			MethodSignature signature = (MethodSignature) point.getSignature();
			Method method = signature.getMethod();

			DataSource ds = method.getAnnotation(DataSource.class);
			if (ds == null) {
				DynamicDataSource.setDataSource(DataSourceNames.MASTER);
				logger.debug("set datasource is " + DataSourceNames.MASTER);
			} else {
				DynamicDataSource.setDataSource(ds.name());
				logger.debug("set datasource is " + ds.name());
			}
			return point.proceed();
		} finally {
			DynamicDataSource.clearDataSource();
			logger.debug("clean datasource");
		}
	}

	/*@Override
	public int getOrder() {
		return -1;
	}*/

}
