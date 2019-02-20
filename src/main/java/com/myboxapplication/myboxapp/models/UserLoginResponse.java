package com.myboxapplication.myboxapp.models;

import com.myboxapplication.myboxapp.models.mysql.Restaurant;
import com.myboxapplication.myboxapp.models.mysql.User;

public class UserLoginResponse {
private User user;
private Restaurant restaurant;

public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Restaurant getRestaurant() {
	return restaurant;
}
public void setRestaurant(Restaurant restaurant) {
	this.restaurant = restaurant;
}

}
