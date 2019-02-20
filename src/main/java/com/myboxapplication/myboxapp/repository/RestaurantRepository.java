package com.myboxapplication.myboxapp.repository;

import com.myboxapplication.myboxapp.codetype.RestaurantSubscribeStatus;
import com.myboxapplication.myboxapp.models.mysql.Restaurant;
import com.myboxapplication.myboxapp.models.mysql.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

    //@Query("select r from restaurant r where r.rest_subscribe_status='approve'")
    Page<Restaurant> findAll(Pageable pageable);

    Restaurant findByRestaurantName(String restaurantName);

    Restaurant findByRestaurantId(long restaurantId);
    
    Restaurant findByUser(User user);

    List<Restaurant> findRestaurantByRestaurantSubscribeStatus(RestaurantSubscribeStatus restaurantSubscribeStatus);


}
