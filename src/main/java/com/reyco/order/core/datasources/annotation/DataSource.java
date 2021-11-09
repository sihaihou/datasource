package com.reyco.order.core.datasources.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.reyco.order.core.datasources.config.DataSourceNames;

/**
 * 多数据源注解
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
	
    String name() default DataSourceNames.MASTER;
    
}
