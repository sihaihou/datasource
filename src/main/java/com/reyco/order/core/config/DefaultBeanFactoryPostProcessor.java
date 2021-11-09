package com.reyco.order.core.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import com.reyco.order.core.datasources.config.DataSourceNames;

/**
*@author reyco
*@date  2021年5月6日---下午2:11:30
*<pre>
*	
*<pre> 
*/
@Component
public class DefaultBeanFactoryPostProcessor implements BeanFactoryPostProcessor{

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		BeanDefinition beanDefinition = beanFactory.getBeanDefinition(DataSourceNames.REYCO);
		MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
		propertyValues.add("password", "Reyco123456.");
		for (PropertyValue propertyValue : propertyValues) {
			System.out.println("propertyValue:"+propertyValue);
		}
		
		String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
		for (String beanName : beanDefinitionNames) {
			BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
			for (PropertyValue propertyValue : bd.getPropertyValues()) {
				System.out.println("propertyValue:"+propertyValue);
			}
		}
	}
}
