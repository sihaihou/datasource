package com.reyco.order.core.datasources.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.reyco.order.core.datasources.pool.ReycoDataSource;

/**
 * 配置多数据源
 */
@Configuration
public class DynamicDataSourceConfig {

    @Bean(name="master",initMethod="init")
    @ConfigurationProperties(prefix ="spring.datasource.master")
    public DataSource masterDataSource() {
    	DataSourceBuilder.create().build();
    	return new DruidDataSource();
    }

    @Bean(name="slave",initMethod="init")
    @ConfigurationProperties(prefix ="spring.datasource.slave")
    public DataSource slaveDataSource() {
        return new DruidDataSource();
    }
    @Bean(name="reyco",initMethod="init")
    @ConfigurationProperties(prefix ="spring.datasource.reyco")
    public DataSource myDataSource() {
    	return new ReycoDataSource();
    }
    
    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("master")DataSource masterDataSource, @Qualifier("slave")DataSource slaveDataSource, @Qualifier("reyco")DataSource reycoDataSource) {
        Map<String, DataSource> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceNames.MASTER, masterDataSource);
        targetDataSources.put(DataSourceNames.SLAVE, slaveDataSource);
        targetDataSources.put(DataSourceNames.REYCO, reycoDataSource);
        return new DynamicDataSource(masterDataSource, targetDataSources);
    }
}
