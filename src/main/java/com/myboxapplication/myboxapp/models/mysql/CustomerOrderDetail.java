package com.myboxapplication.myboxapp.models.mysql;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "customer_order_detail")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CustomerOrderDetail implements Serializable{

	@Id
	@Column(name = "order_cart_detail_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long orderCartDetailId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "customer_order_id", referencedColumnName = "customer_order_id")
	private CustomerOrder customerOrder;
	
	 @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
     private Restaurant restaurant;

	 @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "user_id", referencedColumnName = "user_id")
     private User user;
	 
	@Column(name = "total_amount", nullable = false)
	private double totalAmount;
	
	@Column(name = "total_quantity", nullable = false)
	private int totalQuantity;

	@Column(name = "order_detail_date")
	@CreatedDate
	private String orderDetailDate;
	
	@Column(name = "delivery_time")
    private String deliveryTime;

    public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	
	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public long getOrderCartDetailId() {
		return orderCartDetailId;
	}

	public void setOrderCartDetailId(long orderCartDetailId) {
		this.orderCartDetailId = orderCartDetailId;
	}

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	public String getOrderDetailDate() {
		return orderDetailDate;
	}

	public void setOrderDetailDate(String orderDetailDate) {
		this.orderDetailDate = orderDetailDate;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
