package com.myboxapplication.myboxapp.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.myboxapplication.myboxapp.codetype.RestaurantSubscribeStatus;
import com.myboxapplication.myboxapp.codetype.UserStatus;
import com.myboxapplication.myboxapp.models.mysql.Restaurant;
import com.myboxapplication.myboxapp.models.mysql.User;
import com.myboxapplication.myboxapp.services.RestaurentService;
import com.myboxapplication.myboxapp.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminResource {

	 @Autowired
	  RestaurentService restaurentService;
	 
	 @Autowired
	 UserService userService;
	 
	 @RequestMapping(value = "/restaurantlist", method = RequestMethod.GET)
	    public String getAllRestaurant(Model model, Pageable pageable){
	    	model.addAttribute("restaurants", restaurentService.getAllRestaurants(pageable));
	        return "admin";
	    }
	 @GetMapping
		public String index() {
			return "redirect:/admin/restaurantlist";
		}
	 
	 @ModelAttribute("restaurant")
	 public Page<Restaurant> restaurants(Pageable pageable) {
	         return restaurentService.getAllRestaurants(pageable);
	 }
	 
	 @RequestMapping(value = "/enable/{id}", method = RequestMethod.GET)
	    public String enableRestaurant(@PathVariable("id") long id, Model model,Pageable pageable){
		 	Restaurant restaurant = restaurentService.getByRestaurantId(id);
		 	if(restaurant != null) {
		 		User user = restaurant.getUser();
		 		if(user != null) {
			    	UserStatus userStatus = UserStatus.active;
			    	user.setUserStatus(userStatus);
			    	userService.updateUserStatusByEmailAndPhonenumber(user);
			    }		 	
		 		restaurant.setRestaurantSubscribeStatus(RestaurantSubscribeStatus.approve);
		 	}
		    restaurentService.updateRestaurant(restaurant.getRestaurantId() , restaurant);		    
	    	model.addAttribute("restaurants", restaurentService.getAllRestaurants(pageable));
	        return "admin";
	    }
	 
	 @RequestMapping(value = "/disable/{id}", method = RequestMethod.GET)
	    public String disableRestaurant(@PathVariable("id") long id, Model model,Pageable pageable){
		    Restaurant restaurant = restaurentService.getByRestaurantId(id);
		    if(restaurant != null) {
		    	User user = restaurant.getUser();
		    	if(user != null) {
			    	UserStatus userStatus = UserStatus.inactive;
			    	user.setUserStatus(userStatus);
			    	userService.updateUserStatusByEmailAndPhonenumber(user);
			    }
		    	
		    	
		 		restaurant.setRestaurantSubscribeStatus(RestaurantSubscribeStatus.decline);
		 	}
		    restaurentService.updateRestaurant(restaurant.getRestaurantId() , restaurant);
	    	model.addAttribute("restaurants", restaurentService.getAllRestaurants(pageable));
	        return "admin";
	    }
}
