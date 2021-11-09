package com.reyco.order.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.reyco.order.core.domain.Order;

public interface OrderDao extends BaseDao<Order>{
	
	Integer update(@Param("order")Order order);

	/**
	 * 未支付未过期的订单
	 * @return
	 */
	List<Order> listOrderNoExpire();
	/**
	 * 过期未支付订单
	 * @return
	 */
	Integer updateExpire();
}
