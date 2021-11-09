package com.reyco.order.core.datasources.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reyco.order.core.datasources.annotation.DataSource;
import com.reyco.order.core.datasources.config.DataSourceNames;
import com.reyco.order.core.service.OrderService;
import com.reyco.order.core.utils.R;

/**
 * 测试
 */
//@Service
public class DataSourceTestService {
	
    //@Autowired
    private OrderService orderService;

    public R queryObject(Long userId) {
        return orderService.get(1);
    }

    //@DataSource(name = DataSourceNames.MASTER)
    public R queryObject2(Long userId) {
        return orderService.get(2);
    }
    
    
}
