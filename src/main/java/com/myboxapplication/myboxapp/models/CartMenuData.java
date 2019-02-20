package com.myboxapplication.myboxapp.models;

public class CartMenuData {
	private long foodItemId;
	private double price;
	private Integer quantity;
	
	public long getFoodItemId() {
		return foodItemId;
	}
	public void setFoodItemId(long foodItemId) {
		this.foodItemId = foodItemId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
