package com.myboxapplication.myboxapp.models.mysql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myboxapplication.myboxapp.codetype.RestaurantRole;
import com.myboxapplication.myboxapp.codetype.RestaurantSubscribeStatus;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "restaurant")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Restaurant implements Serializable {
    @Id
    @Column(name = "restaurant_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long restaurantId;

    @Column(nullable = false, name = "restaurant_name")
    private String restaurantName;

    @Column(name = "restaurant_addrress",nullable = false)
    private String restaurantAddress;

    @Column(name = "restaurant_location",nullable = false)
    private String restaurantLocation;

    @Column(name = "restaurant_rating")
    private Double  restaurantRating;

    @Column(name = "rest_subscribe_date")
    private String restSubscribeDate ;

    @Column(name = "rest_subscribe_status")
    @Enumerated(EnumType.STRING)
    private RestaurantSubscribeStatus restaurantSubscribeStatus;
    
    @Column(name = "rest_role_type")
    @Enumerated(EnumType.STRING)
    private RestaurantRole restaurantRole;
    
    @Column(name = "employee_Rest_Id")
    private Long employeeRestId;

    @Column(name = "total_revenue")
    private Long totalRevenue;

    @Column(name = "total_orders")
    private Long totalOrders;
    
    @Column(name = "delivery_charges")
    private Long deliveryCharges;
    
    @JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private User user;

    @NotEmpty(message = "Please provide phonenumber")
    @Column(nullable = false,name = "rest_phone_number", unique = true)
    private String phoneNumber;

    @Column(nullable = false,name = "email", unique = true)
   // @Email(message = "Please provide a valid e-mail")
    @NotEmpty(message = "Please provide an e-mail")
    private String email;

    public RestaurantRole getRestaurantRole() {
		return restaurantRole;
	}

	public void setRestaurantRole(RestaurantRole restaurantRole) {
		this.restaurantRole = restaurantRole;
	}

	public Long getEmployeeRestId() {
		return employeeRestId;
	}

	public void setEmployeeRestId(Long employeeRestId) {
		this.employeeRestId = employeeRestId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
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

    public String getRestSubscribeDate() {
        return restSubscribeDate;
    }

    public void setRestSubscribeDate(String string) {
        this.restSubscribeDate = string;
    }

    public RestaurantSubscribeStatus getRestaurantSubscribeStatus() {
        return restaurantSubscribeStatus;
    }

    public void setRestaurantSubscribeStatus(RestaurantSubscribeStatus restaurantSubscribeStatus) {
        this.restaurantSubscribeStatus = restaurantSubscribeStatus;
    }

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

	public Long getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(Long totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public Long getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(Long totalOrders) {
		this.totalOrders = totalOrders;
	}

	public Long getDeliveryCharges() {
		return deliveryCharges;
	}

	public void setDeliveryCharges(Long deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}

}
