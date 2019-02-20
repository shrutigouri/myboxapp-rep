
package com.myboxapplication.myboxapp.models.mysql;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myboxapplication.myboxapp.codetype.FoodItemStatus;
import com.myboxapplication.myboxapp.codetype.FoodType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "restaurant_menu_items")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Menu implements Serializable {
    @Id
    @Column(name = "food_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long foodItemId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id",referencedColumnName = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "food_item_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    @Column(name = "food_item_name",nullable = false)
    private String foodItemName;

    @Column(name = "food_item_price",nullable = false)
    private Double foodItemPrice;

    @Lob
    @Column(name = "food_item_description")
    private String foodItemDescription;

    @Column(name = "food_image")
    private String foodImage;
    
    @Column(name = "currency",nullable = false)
    private String currency;
    
	@Enumerated(EnumType.STRING)
    @Column(name = "food_item_status")
    private FoodItemStatus foodItemStatus;

    @Column(name = "food_rating")
    private Double  foodRating;

    public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

    public long getFoodItemId() {
        return foodItemId;
    }

    public void setFoodItemId(long foodItemId) {
        this.foodItemId = foodItemId;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public String getFoodItemName() {
        return foodItemName;
    }

    public void setFoodItemName(String foodItemName) {
        this.foodItemName = foodItemName;
    }

    public Double getFoodItemPrice() {
        return foodItemPrice;
    }

    public void setFoodItemPrice(Double foodItemPrice) {
        this.foodItemPrice = foodItemPrice;
    }

    public String getFoodItemDescription() {
        return foodItemDescription;
    }

    public void setFoodItemDescription(String foodItemDescription) {
        this.foodItemDescription = foodItemDescription;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public FoodItemStatus getFoodItemStatus() {
        return foodItemStatus;
    }

    public void setFoodItemStatus(FoodItemStatus foodItemStatus) {
        this.foodItemStatus = foodItemStatus;
    }

    public Double  getFoodRating() {
        return foodRating;
    }

    public void setFoodRating(Double  foodRating) {
        this.foodRating = foodRating;
    }
}




