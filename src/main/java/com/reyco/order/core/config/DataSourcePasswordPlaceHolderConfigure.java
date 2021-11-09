/**
 * 
 */
package com.reyco.order.core.config;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PlaceholderConfigurerSupport;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.reyco.order.core.datasources.config.DataSourceNames;

/** 
* @author reyco
* @date 2021年8月25日  
*/
@Component
public class DataSourcePasswordPlaceHolderConfigure extends PlaceholderConfigurerSupport implements Ordered{

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
			throws BeansException {
		BeanDefinition beanDefinition = beanFactory.getBeanDefinition(DataSourceNames.REYCO);
		MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
		PropertyValue propertyValue = propertyValues.getPropertyValue("password");
	}
	@Override
	public int getOrder() {
		return LOWEST_PRECEDENCE;
	}
}
