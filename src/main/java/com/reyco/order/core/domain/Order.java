package com.reyco.order.core.domain;

import java.util.Date;

public class Order extends Base{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7060691960102038201L;
	private String no;
	private String orderState;
	private Date gmtExpire;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public Date getGmtExpire() {
		return gmtExpire;
	}
	public void setGmtExpire(Date gmtExpire) {
		this.gmtExpire = gmtExpire;
	}
}
