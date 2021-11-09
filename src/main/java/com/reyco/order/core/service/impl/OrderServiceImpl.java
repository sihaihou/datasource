package com.reyco.order.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.reyco.order.core.dao.OrderDao;
import com.reyco.order.core.domain.Order;
import com.reyco.order.core.service.OrderService;
import com.reyco.order.core.utils.R;

@Service("orderService")
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDao orderDao;
	
	@Value(value = "${com.reyco.order.page.pageSize}")
	private Integer pageSize;
	
	@Override
	public R get(Integer id) {
		Order order = orderDao.get(id);
		order.setOrderState(getState(order.getState()));
		return R.success(order);
	}
	
	@Override
	public R list(Integer pageNo,Integer pageSize,Order order) {
		if(null==pageSize) {
			pageSize=this.pageSize;
		}
		PageHelper.startPage(pageNo,pageSize);
		List<Order> list = orderDao.list(order);
		for (Order temp : list) {
			temp.setOrderState(getState(temp.getState()));
		}
		PageInfo<Order> pageInfo = new PageInfo<Order>(list);
		return R.success(pageInfo);
	}
	
	/**
	 * 提供延时队列
	 */
	@Override
	public synchronized void save(Order order) {
	
	}
	
	@Transactional
	@Override
	public R update(Order order) {
		 orderDao.update(order);
		 orderDao.delete(order.getId());
		 return R.success("OK");
	}

	@Override
	public void mqSave(Order order) {
	}
	
	public String getState(Integer state) {
		Map<Integer, String> map = new HashMap<Integer,String>();
		map.put(1, "未付款");
		map.put(2, "已付款");
		map.put(3, "订单关闭");
		map.put(4, "订单完成");
		return map.get(state);
	}

	@Override
	public R delete(Integer id) {
		Integer count = orderDao.delete(id);
		if(count==1) {
			return R.success("删除成功");
		}
		return R.fail("删除失败");
	}
	
	
}
