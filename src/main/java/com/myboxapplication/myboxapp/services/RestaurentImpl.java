package com.myboxapplication.myboxapp.services;

import com.myboxapplication.myboxapp.codetype.RestaurantSubscribeStatus;
import com.myboxapplication.myboxapp.exceptions.RestaurantNotFoundException;
import com.myboxapplication.myboxapp.models.UserLoginResponse;
import com.myboxapplication.myboxapp.models.mysql.Restaurant;
import com.myboxapplication.myboxapp.models.mysql.User;
import com.myboxapplication.myboxapp.repository.RestaurantRepository;
import com.myboxapplication.myboxapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurentImpl implements RestaurentService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    RestaurentService restaurentService;
    
    @Autowired
    UserRepository userRepository;

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getByRestaurantName(String restaurantName) {
        return restaurantRepository.findByRestaurantName(restaurantName);
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);

    }

    @Override
    public Restaurant updateRestaurant(long restaurantId, Restaurant restaurant) {

        Restaurant restaurant1 = restaurantRepository.findByRestaurantId(restaurantId);

        if (restaurant1==null){

            throw new RestaurantNotFoundException("Restaurant not Found= " +restaurantId);
        }

        restaurant1.setEmail(restaurant.getEmail());
        restaurant1.setPhoneNumber(restaurant.getPhoneNumber());
        restaurant1.setRestaurantName(restaurant.getRestaurantName());
        restaurant1.setRestaurantAddress(restaurant.getRestaurantAddress());
        restaurant1.setRestaurantLocation(restaurant.getRestaurantLocation());
        restaurant1.setEmail(restaurant.getEmail());
        restaurant1.setRestaurantSubscribeStatus(restaurant.getRestaurantSubscribeStatus());
        restaurant1.setRestSubscribeDate(restaurant.getRestSubscribeDate());
        return restaurantRepository.save(restaurant1);
    }

    @Override
    public List<Restaurant> getRestaurantByRestaurantSubscribeStatus(RestaurantSubscribeStatus restaurantSubscribeStatus) {
    	List<Restaurant> restList= restaurantRepository.findRestaurantByRestaurantSubscribeStatus(restaurantSubscribeStatus.approve);
    	/*if (restList == null || restList.isEmpty()) {
    		 throw new RestaurantNotFoundException("Restaurant not Found");
    	} */
    	return restList;
    }

    @Override
    public Restaurant getByRestaurantId(long restaurantId) {
        return restaurantRepository.findByRestaurantId(restaurantId);
    }
    
    @Override
    public Restaurant getByUser(long userId) {
    	User user = userRepository.findByUserId(userId);
    	Restaurant restaurant =  null;
    	if(user!= null) 
    		restaurant = restaurantRepository.findByUser(user);    	
    	return restaurant;
    }

	@Override
	public Restaurant updateEmpByRestaurantId(long restaurantId) {
		// TODO Auto-generated method stub
		return null;
	}
}
