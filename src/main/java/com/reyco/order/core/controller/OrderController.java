package com.reyco.order.core.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reyco.order.core.datasources.annotation.DataSource;
import com.reyco.order.core.datasources.config.DataSourceNames;
import com.reyco.order.core.domain.Order;
import com.reyco.order.core.service.OrderService;
import com.reyco.order.core.utils.R;

@RestController
@RequestMapping("/api/order")
public class OrderController{

	@Autowired
	private OrderService orderService;
	
	@DataSource(name=DataSourceNames.REYCO)
	@RequestMapping("get")
	public String get(HttpServletResponse response,Integer id) throws IOException {
		Cookie cookie = new Cookie("localhost", "21e4ojqkadwald");
		response.addCookie(cookie);
		return orderService.get(id).toJSON();
	}
	
	@DataSource(name=DataSourceNames.SLAVE)
	@RequestMapping("/{id}")
	public String info(@PathVariable(name="id") Integer id) {
		return orderService.get(id).toJSON();
	}
	
	
	@RequestMapping("getId")
	public String getId(Integer id) {
		return orderService.get(id).toJSON();
	}
	@DataSource(name=DataSourceNames.SLAVE)
	@GetMapping("list")
	public String list(@RequestParam(defaultValue="1",name="pageNo",required=true)Integer pageNo,Integer pageSize,Order order) {
		return orderService.list(pageNo,pageSize,order).toJSON();
	}
	
	@RequestMapping("save")
	public String save(Order order) {
		orderService.save(order);
		return "ok";
	}
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		R r = orderService.delete(id);
		return r.toJSON();
	}
	
	@DataSource(name=DataSourceNames.REYCO)
	@PostMapping("update")
	public String update(Order order) {
		order.setId(1);
		order.setDesc("test");
		order.setName("order1");
		R r = orderService.update(order);
		return r.toJSON();
	}
	
	
}
