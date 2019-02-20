package com.myboxapplication.myboxapp.models;

import java.util.List;

import com.myboxapplication.myboxapp.models.mysql.Cart;

public class OrderCartDetailResonse {
	
	private String customerOrderId;
	private List<Cart> cart; 
	private String userName;
	private String restaurantName;
	private String restaurantAddress;
	private String location;
	private double totalAmount;
    private Integer totalQuatity;
    
    public String getRestaurantAddress() {
		return restaurantAddress;
	}
	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}
	
	public String getCustomerOrderId() {
		return customerOrderId;
	}
	public void setCustomerOrderId(String customerOrderId) {
		this.customerOrderId = customerOrderId;
	}
	public List<Cart> getCart() {
		return cart;
	}
	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Integer getTotalQuatity() {
		return totalQuatity;
	}
	public void setTotalQuatity(Integer totalQuatity) {
		this.totalQuatity = totalQuatity;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

}
