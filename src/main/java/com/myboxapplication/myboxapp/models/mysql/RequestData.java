package com.myboxapplication.myboxapp.models.mysql;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.myboxapplication.myboxapp.codetype.LoginType;
import com.myboxapplication.myboxapp.codetype.UserRole;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class RequestData {
	private String userId;

	private String phoneNumber;

	private String email;

	private String username;

	@JsonProperty
	private String password;
	
	private long restaurantId;

	public long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}

	private String restaurantName;

	private String restaurantAddress;

	private String restaurantLocation;

	private Double  restaurantRating;

	@Enumerated(EnumType.STRING)
	private UserRole userRole;

	private String userLoginDate;

	@Enumerated(EnumType.STRING)
	private LoginType userLoginType;

	@Enumerated(EnumType.STRING)
	private LoginType userLastLoginType;

	private String restSubscribeDate;


	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantAddress() {
		return restaurantAddress;
	}

	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}

	public String getRestaurantLocation() {
		return restaurantLocation;
	}

	public void setRestaurantLocation(String restaurantLocation) {
		this.restaurantLocation = restaurantLocation;
	}

	public Double  getRestaurantRating() {
		return restaurantRating;
	}

	public void setRestaurantRating(Double  restaurantRating) {
		this.restaurantRating = restaurantRating;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public String getUserLoginDate() {
		return userLoginDate;
	}

	public void setUserLoginDate(String userLoginDate) {
		this.userLoginDate = userLoginDate;
	}

	public LoginType getUserLoginType() {
		return userLoginType;
	}

	public void setUserLoginType(LoginType userLoginType) {
		this.userLoginType = userLoginType;
	}

	public LoginType getUserLastLoginType() {
		return userLastLoginType;
	}

	public void setUserLastLoginType(LoginType userLastLoginType) {
		this.userLastLoginType = userLastLoginType;
	}

	public String getRestSubscribeDate() {
		return restSubscribeDate;
	}

	public void setRestSubscribeDate(String restSubscribeDate) {
		this.restSubscribeDate = restSubscribeDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
