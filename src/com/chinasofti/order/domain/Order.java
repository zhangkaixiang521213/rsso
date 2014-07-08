
package com.chinasofti.order.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import core.domain.DomainBase;
import core.mybatis.MyBatisDomain;

@MyBatisDomain
public class Order extends DomainBase{
	protected String orderId;
	private String oname;
	private String oaddress;
	private String phone;
	private List<OrderItem> orderItems;

	public Order() {
		
	}

	public Order(String orderId) {
		this.orderId = orderId;
	}
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getOname() {
		return oname;
	}
	public void setOname(String oname) {
		this.oname = oname;
	}

	public String getOaddress() {
		return oaddress;
	}
	public void setOaddress(String oaddress) {
		this.oaddress = oaddress;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}