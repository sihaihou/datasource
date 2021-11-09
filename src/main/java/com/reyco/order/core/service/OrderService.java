package com.reyco.order.core.service;


import com.reyco.order.core.domain.Order;
import com.reyco.order.core.utils.R;

public interface OrderService {
	
	R get(Integer id);
	
	R list(Integer pageNo,Integer pageSize,Order order);
	
	/**
	 * 更新
	 */
	R update(Order order);
	/**
	 * 保存
	 * @param order
	 */
	void save(Order order);
	/**
	 * 
	 * @param order
	 */
	void mqSave(Order order);
	
	R delete(Integer id);
}
