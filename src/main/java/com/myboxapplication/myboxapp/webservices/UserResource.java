
package com.myboxapplication.myboxapp.webservices;

import com.myboxapplication.myboxapp.codetype.UserRole;
import com.myboxapplication.myboxapp.codetype.UserStatus;
import com.myboxapplication.myboxapp.exceptions.UserNotFoundException;
import com.myboxapplication.myboxapp.models.UserLoginResponse;
import com.myboxapplication.myboxapp.models.mysql.RequestData;
import com.myboxapplication.myboxapp.models.mysql.Restaurant;
import com.myboxapplication.myboxapp.models.mysql.User;
import com.myboxapplication.myboxapp.repository.UserRepository;
import com.myboxapplication.myboxapp.services.ResponseGenerator;
import com.myboxapplication.myboxapp.services.RestaurentService;
import com.myboxapplication.myboxapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {
	Logger logger = LoggerFactory.getLogger(UserResource.class);
    @Autowired
    UserService userService;
    
    @Autowired
    RestaurentService restaurentService;

    @Autowired
    ResponseGenerator responseGenerator;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/public")
    public ResponseEntity getAllUsers() {
        List<User> user = userService.getAllUsers();
        if(user != null & !user.isEmpty()) {
        	return ResponseEntity.ok(responseGenerator
                .success(user,"user.list"));
        }else
       	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("getbyphnumber/{phone_no}/public")
    public ResponseEntity getUserByPhoneNo(@PathVariable("phone_no") String phoneNumber) {
    	 User user = userService.getUserByPhoneNumber(phoneNumber);
         if(user != null) {
        	 UserLoginResponse userLoginResponse = new UserLoginResponse();
        	 user.setPassword(null);
        	 userLoginResponse.setUser(user);
         	return ResponseEntity.ok(responseGenerator
                 .success(userLoginResponse,"user.found"));
         }else
        	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("getbyemail/{email}/public")
    public ResponseEntity getUserByEmail(@PathVariable("email") String email) {
    	 User user = userService.getUserByEmail(email);
         if(user != null) {
        	 UserLoginResponse userLoginResponse = new UserLoginResponse();
        	 user.setPassword(null);
        	 userLoginResponse.setUser(user);
         	return ResponseEntity.ok(responseGenerator
                 .success(userLoginResponse,"user.found"));
         } else
        	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}/user/public")
    public ResponseEntity getUserById(@PathVariable("id") long userId) {      
        User user = userService.getUserByUserId(userId);
        if(user != null) {
       	 UserLoginResponse userLoginResponse = new UserLoginResponse();
       	 user.setPassword(null);
       	 userLoginResponse.setUser(user);
        	return ResponseEntity.ok(responseGenerator
                .success(userLoginResponse,"user.found"));
        } else
       	 	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("update/{id}/public")
    public ResponseEntity updateUser(@RequestBody RequestData requestData,@PathVariable("id") long userId){
        if(requestData.getPassword() != null)
        	requestData.setPassword(bCryptPasswordEncoder.encode(requestData.getPassword()));
        UserLoginResponse userResp = userService.updateUser(requestData,userId);
        if(userResp != null) {
	        return ResponseEntity
	                .ok(responseGenerator
	                        .success(userResp,"user.updated"));
    	}
    	else {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }


    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody RequestData requestData) {
        requestData.setPassword(bCryptPasswordEncoder.encode(requestData.getPassword()));
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        User userExists = userService.getUserByEmailAndPhoneNumber(requestData.getEmail(),requestData.getPhoneNumber());
        if(userExists != null) {
        	String userResp = userExists.getUserId() + " user with phone number "+userExists.getPhoneNumber() + " already exists";
        	logger.info(userResp);
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
        	 User user = userService.AddUserLoginDetail(requestData);
        	 userLoginResponse.setUser(user);
        	 if (requestData.getUserRole().equals(UserRole.vendor) || requestData.getUserRole().equals(UserRole.employee)) {
             	Restaurant restaurant = userService.AddVendorUserLoginDetail(requestData, user);
                user.setPassword(null);
             	userLoginResponse.setRestaurant(restaurant);
        	 }
        if(userLoginResponse != null)
        	return ResponseEntity.status(HttpStatus.CREATED).body(responseGenerator.success(userLoginResponse,"user.signup"));
        else {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        }  	
    }


    @GetMapping("/getuser/{email}/{phone_no}/public")
    public ResponseEntity getUserByPhoneNo(@PathVariable("email") String email,@PathVariable("phone_no") String phoneNumber) {
        User user = userService.getUserByEmailAndPhoneNumber(email,phoneNumber);
    	UserLoginResponse userLoginResponse = new UserLoginResponse(); 
        if(user != null) {
        	user.setPassword(null);    
        	userLoginResponse.setUser(user);
        	return ResponseEntity.ok(responseGenerator
                .success(userLoginResponse,"user.found"));
        }else
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("update/pwd/public")
    public ResponseEntity updateUserByEmailAndPassword(@RequestBody User user){
    	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    	int updateSts = userService.updateUserByEmailAndPhonenumber(user);
    	UserLoginResponse userLoginResponse = new UserLoginResponse();
        user.setPassword(null);
    	userLoginResponse.setUser(user);
    	if(updateSts == 1) {
	        return ResponseEntity
	                .ok(responseGenerator
	                        .success(userLoginResponse,"user.updated"));
    	}
    	else {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }
    

    @PostMapping("/verifyuser/public")
    public ResponseEntity checkUserLogin(@RequestBody RequestData requestData) {
    	User user = null;
    	boolean validPwd = false;
    	UserLoginResponse userLoginResponse = new UserLoginResponse();
        User userExists = userService.getUserByEmailAndPhoneNumber(requestData.getEmail(), requestData.getPhoneNumber());
        if(userExists != null) {
        	
        validPwd = bCryptPasswordEncoder.matches(requestData.getPassword(), userExists.getPassword());
        if (userExists != null && validPwd && userExists.getUserRole().equals(requestData.getUserRole())) {
        	user = userService.updateUserLoginStatus(userExists, userExists.getUserId());
            user.setPassword(null);
            if (user.getUserRole().equals(UserRole.vendor) || user.getUserRole().equals(UserRole.employee)) {
            	userLoginResponse.setRestaurant(restaurentService.getByUser(user.getUserId()));
            }
            userLoginResponse.setUser(user);
        	 return ResponseEntity.ok(responseGenerator
                     .success(userLoginResponse,"user.found"));
        }
        }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PutMapping("update/status/public")
    public ResponseEntity updateUserStatusByEmailAndPassword(@RequestBody User user){
    	int updateSts = userService.updateUserStatusByEmailAndPhonenumber(user);
    	UserLoginResponse userLoginResponse = new UserLoginResponse();
        user.setPassword(null);
    	userLoginResponse.setUser(user);
    	if(updateSts == 1) {
	        return ResponseEntity
	                .ok(responseGenerator
	                        .success(userLoginResponse,"user.updated"));
    	}
    	else {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }
}

