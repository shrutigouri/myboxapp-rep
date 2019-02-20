package com.myboxapplication.myboxapp.models;

import java.util.List;

public class OrderCartRequestData {
	
	private long userId;
	private long restaurantId;
	private long paymentId;
	private String customerOrderId;
	private long cartId;
	private List<CartMenuData> menuList;
	private String orderDate;
	private String OrderDeliveryTime;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}
	public long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}
	public String getCustomerOrderId() {
		return customerOrderId;
	}
	public void setCustomerOrderId(String customerOrderId) {
		this.customerOrderId = customerOrderId;
	}
	public long getCartId() {
		return cartId;
	}
	public void setCartId(long cartId) {
		this.cartId = cartId;
	}
	public List<CartMenuData> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<CartMenuData> menuList) {
		this.menuList = menuList;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderDeliveryTime() {
		return OrderDeliveryTime;
	}
	public void setOrderDeliveryTime(String orderDeliveryTime) {
		OrderDeliveryTime = orderDeliveryTime;
	}

}
