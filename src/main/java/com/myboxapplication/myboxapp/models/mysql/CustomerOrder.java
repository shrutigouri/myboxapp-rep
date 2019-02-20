package com.myboxapplication.myboxapp.models.mysql;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.myboxapplication.myboxapp.codetype.CustRatingStatus;
import com.myboxapplication.myboxapp.codetype.DeliveryFoodStatus;
import com.myboxapplication.myboxapp.codetype.OrderOverallStatus;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customer_order")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CustomerOrder implements Serializable {

    @Id
    @Column(name = "customer_order_id")
    private String customerOrderId;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_id",referencedColumnName = "payment_id")
    private CustomerPaymentDetail customerPaymentDetail;
  
   	@Column(name = "cust_order_date")
    @CreatedDate
    private String custOrderDate;
    	
	@Enumerated(EnumType.STRING)
    @Column(name = "order_overall_status")
    private OrderOverallStatus orderOverallStatus;
    
    @Column(name = "progress_comment")
    private String orderProgressComment;
    
    @Column(name = "cust_rating")
    private double custRating;

    @Enumerated(EnumType.STRING)
    @Column(name = "cust_rating_status")
    private CustRatingStatus custRatingStatus;
    

	@Column(name = "delivery_food_status")
    private DeliveryFoodStatus deliveryFoodStatus;
    
   	public OrderOverallStatus getOrderOverallStatus() {
		return orderOverallStatus;
	}

	public void setOrderOverallStatus(OrderOverallStatus orderOverallStatus) {
		this.orderOverallStatus = orderOverallStatus;
	}

	public String getOrderProgressComment() {
		return orderProgressComment;
	}

	public void setOrderProgressComment(String orderProgressComment) {
		this.orderProgressComment = orderProgressComment;
	}

	public DeliveryFoodStatus getDeliveryFoodStatus() {
		return deliveryFoodStatus;
	}

	public void setDeliveryFoodStatus(DeliveryFoodStatus deliveryFoodStatus) {
		this.deliveryFoodStatus = deliveryFoodStatus;
	}
    
    public String getCustOrderDate() {
        return custOrderDate;
    }

    public void setCustOrderDate(String custOrderDate) {
        this.custOrderDate = custOrderDate;
    }

    public double getCustRating() {
        return custRating;
    }

    public void setCustRating(double custRating) {
        this.custRating = custRating;
    }

    public CustRatingStatus getCustRatingStatus() {
        return custRatingStatus;
    }

    public void setCustRatingStatus(CustRatingStatus custRatingStatus) {
        this.custRatingStatus = custRatingStatus;
    }
    
   	public String getCustomerOrderId() {
		return customerOrderId;
	}

	public void setCustomerOrderId(String customerOrderId) {
		this.customerOrderId = customerOrderId;
	}

	public CustomerPaymentDetail getCustomerPaymentDetail() {
		return customerPaymentDetail;
	}

	public void setCustomerPaymentDetail(CustomerPaymentDetail customerPaymentDetail) {
		this.customerPaymentDetail = customerPaymentDetail;
	}
}

