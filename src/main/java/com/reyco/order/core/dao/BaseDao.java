package com.reyco.order.core.dao;

import java.util.List;


public interface BaseDao<T> {
	/**
	 * 查询
	 * @param obj
	 */
	List<T> list(T t);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	T get(Integer id);
	/**
	 * 保存
	 * @param t
	 */
	void save(T t);
	/**
	 * 保存
	 * @param t
	 */
	void insert(T t);
	/**
	 * 
	 * @param t
	 * @return
	 */
	Integer count(T t);
	
	Integer delete(Integer id);
}
