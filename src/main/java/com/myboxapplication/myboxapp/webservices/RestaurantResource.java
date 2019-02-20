package com.myboxapplication.myboxapp.webservices;


import com.myboxapplication.myboxapp.codetype.RestaurantSubscribeStatus;
import com.myboxapplication.myboxapp.models.mysql.Restaurant;
import com.myboxapplication.myboxapp.services.ResponseGenerator;
import com.myboxapplication.myboxapp.services.RestaurentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
public class RestaurantResource  {

    @Autowired
    RestaurentService restaurentService;

    @Autowired
    ResponseGenerator responseGenerator;

    @GetMapping("public")
    public ResponseEntity getAllRestaurant(Pageable pageable){
        return ResponseEntity.ok(responseGenerator.success(restaurentService.getAllRestaurants(pageable),"resteraunt.lists"));
    }

    @GetMapping("{restaurant_name}/public")
    public ResponseEntity getRestaurantByName(@PathVariable("restaurant_name") String restaurantName){
        return ResponseEntity
                .ok(responseGenerator
                        .success(restaurentService.getByRestaurantName(restaurantName),"restaurant.found"));
    }

    @GetMapping("{id}/restaurant/public")
    public ResponseEntity getByRestaurantId(@PathVariable("id") long restaurantId){
        return ResponseEntity
                .ok(responseGenerator
                        .success(restaurentService.getByRestaurantId(restaurantId),"restaurant.found"));
    }
    
    @GetMapping("get/user/{id}/public")
    public ResponseEntity getByUserId(@PathVariable("id") long userId){
        return ResponseEntity
                .ok(responseGenerator
                        .success(restaurentService.getByUser(userId),"restaurant.found"));
    }
    
    @PutMapping("update/employee/{id}/public")
    public ResponseEntity updateEmployeeByRestId(@PathVariable("id") long restaurantId){
        return ResponseEntity
                .ok(responseGenerator
                        .success(restaurentService.updateEmpByRestaurantId(restaurantId),"restaurant.found"));
    }

    @PostMapping("public")
    public ResponseEntity addRestaurent(@RequestBody Restaurant restaurant) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseGenerator
                        .success(restaurentService.addRestaurant(restaurant),"restaurant.added"));
    }

    @PutMapping("update/{id}/public")
    public ResponseEntity updateUser(@RequestBody Restaurant restaurant,@PathVariable("id") long restaurantId){
        return ResponseEntity
                .ok(responseGenerator
                        .success(restaurentService.updateRestaurant(restaurantId,restaurant),"restaurant.updated"));
    }

    @GetMapping("restaurantSubscribeStatus/public")
    public ResponseEntity getByRestaraurentSubscribeStatus(@RequestParam(value = "restaurantSubscribeStatus", required = true) RestaurantSubscribeStatus restaurantSubscribeStatus) {
    	List<Restaurant> restaurants = restaurentService.getRestaurantByRestaurantSubscribeStatus(restaurantSubscribeStatus);
    	if (!restaurants.isEmpty() && restaurants != null && restaurants.size() > 0)
    	return ResponseEntity
                .ok(responseGenerator.success(restaurentService.getRestaurantByRestaurantSubscribeStatus(restaurantSubscribeStatus),"approve.rest.found"));
    	else
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
