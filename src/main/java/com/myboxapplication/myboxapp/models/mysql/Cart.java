package com.myboxapplication.myboxapp.models.mysql;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cart implements Serializable {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cartId;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "customer_order_id", referencedColumnName = "customer_order_id")
	private CustomerOrder customerOrderId;

    @Column(name = "price",nullable = false)
    private double price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "food_item_id",referencedColumnName = "food_item_id")
    private Menu menu;

    @Column(name = "order_cart_date")
    @CreatedDate
    private String orderCartDate;

    public double getAmount() {
        return (this.price * this.quantity);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public String getOrderCartDate() {
		return orderCartDate;
	}

	public void setOrderCartDate(String orderCartDate) {
		this.orderCartDate = orderCartDate;
	}

	public CustomerOrder getCustomerOrderId() {
		return customerOrderId;
	}

	public void setCustomerOrderId(CustomerOrder customerOrderId) {
		this.customerOrderId = customerOrderId;
	}

}
