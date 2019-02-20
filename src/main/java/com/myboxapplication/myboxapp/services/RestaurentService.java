package com.myboxapplication.myboxapp.services;

import com.myboxapplication.myboxapp.codetype.RestaurantSubscribeStatus;
import com.myboxapplication.myboxapp.models.mysql.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RestaurentService {

    Page<Restaurant> getAllRestaurants(Pageable pageable);

    Restaurant getByRestaurantName(String restaurantName);

    Restaurant addRestaurant(Restaurant restaurant);

    Restaurant updateRestaurant(long restaurantId, Restaurant restaurant);

   // Restaurant updateRestaurant(String email, String phoneNumber, Restaurant restaurant);

    List<Restaurant> getRestaurantByRestaurantSubscribeStatus(RestaurantSubscribeStatus restaurantSubscribeStatus);

    Restaurant getByRestaurantId(long restaurantId);
    
    Restaurant updateEmpByRestaurantId(long restaurantId);

    Restaurant getByUser(long userId);

}
