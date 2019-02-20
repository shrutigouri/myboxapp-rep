
package com.myboxapplication.myboxapp.services;

import com.myboxapplication.myboxapp.codetype.RestaurantRole;
import com.myboxapplication.myboxapp.codetype.RestaurantSubscribeStatus;
import com.myboxapplication.myboxapp.codetype.UserRole;
import com.myboxapplication.myboxapp.codetype.UserStatus;
import com.myboxapplication.myboxapp.models.UserLoginResponse;
import com.myboxapplication.myboxapp.models.mysql.RequestData;
import com.myboxapplication.myboxapp.models.mysql.Restaurant;
import com.myboxapplication.myboxapp.models.mysql.User;
import com.myboxapplication.myboxapp.repository.RestaurantRepository;
import com.myboxapplication.myboxapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    RestaurantRepository restaurantRepository;

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findUserByPhoneNumber(phoneNumber);
    }

    @Override
    public User getUserByUserId(long userId) {
        return userRepository.findByUserId(userId);
    }

    public User AddUserLoginDetail(RequestData reqData) {
        String statusMsg = null;
        User user = null;
        Restaurant rest = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

                long timeNow = Calendar.getInstance().getTimeInMillis();
                user = new User();
                user.setPhoneNumber(reqData.getPhoneNumber());
                user.setEmail(reqData.getEmail());
                user.setUsername(reqData.getUsername());
                user.setPassword(reqData.getPassword());
                user.setUserRole(reqData.getUserRole());
                user.setUserLoginDate(reqData.getUserLoginDate());
                user.setUserLoginType(reqData.getUserLoginType());
                user.setUserLastLoginType(reqData.getUserLastLoginType());
                if (reqData.getUserRole().equals(UserRole.vendor) || reqData.getUserRole().equals(UserRole.employee)) 
                	user.setUserStatus(UserStatus.inactive);
                else
                	user.setUserStatus(UserStatus.active);
                userRepository.save(user);

            statusMsg = reqData.getUsername() + " Account is saved successfully";
            if(logger.isDebugEnabled()) {
                logger.debug(statusMsg);
            }
            logger.info(statusMsg);
        }catch(Exception e) {
            statusMsg = reqData.getUsername() + " Account is failed to store the user details";
            logger.error(e.getMessage());
            user = null;
        }
        return user;
    }
    
    public Restaurant AddVendorUserLoginDetail(RequestData reqData, User user) {
        String statusMsg = null;
        Restaurant rest = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

            if (reqData.getUserRole().equals(UserRole.vendor)) {
                rest = new Restaurant();
                rest.setEmail(reqData.getEmail());
                rest.setPhoneNumber(reqData.getPhoneNumber());
                rest.setRestaurantName(reqData.getRestaurantName());
                rest.setRestaurantAddress(reqData.getRestaurantAddress());
                rest.setRestaurantLocation(reqData.getRestaurantLocation());
                rest.setRestaurantRating(reqData.getRestaurantRating());
                rest.setRestSubscribeDate(reqData.getRestSubscribeDate());
                rest.setRestaurantSubscribeStatus(RestaurantSubscribeStatus.pending);
                rest.setUser(user);
                rest.setRestaurantRole(RestaurantRole.owner);
                restaurantRepository.save(rest);
            }  else {
            	 rest = new Restaurant();
                 rest.setEmail(reqData.getEmail());
                 rest.setPhoneNumber(reqData.getPhoneNumber());
                 rest.setRestaurantName(reqData.getRestaurantName());
                 rest.setRestaurantAddress(reqData.getRestaurantAddress());
                 rest.setRestaurantLocation(reqData.getRestaurantLocation());
                 rest.setRestaurantRating(reqData.getRestaurantRating());
                 rest.setRestSubscribeDate(reqData.getRestSubscribeDate());
                 rest.setRestaurantSubscribeStatus(RestaurantSubscribeStatus.pending);
                 rest.setUser(user);
                 rest.setRestaurantRole(RestaurantRole.employee);
                 rest.setEmployeeRestId(reqData.getRestaurantId());
                 restaurantRepository.save(rest);
            }

            statusMsg = reqData.getUsername() + "Restaurant Account is saved successfully";
            if(logger.isDebugEnabled()) {
                logger.debug(statusMsg);
            }
            logger.info(statusMsg);
        }catch(Exception e) {
            statusMsg = reqData.getUsername() + " Account is failed to store the Restaurant details";
            logger.error(e.getMessage());
            rest = null;
        }
        return rest;
    }

    @Override
    public User getUserByEmailAndPhoneNumber(String email, String phoneNumber) {
        User user = null;
    	String statusMsg = null;
      try {
           user = userRepository.findUserByEmailAndPhoneNumber(email,phoneNumber);          
           statusMsg = email + "Account is fetched successfully";
           if(logger.isDebugEnabled()) {
               logger.debug(statusMsg);
           }
           logger.info(statusMsg);
      }catch(Exception e) {
          statusMsg = user.getUsername() + " Account is failed to fetch a details";
          logger.error(e.getMessage());
          user = null;
      }
        return user;
    }


    @Override
    public int updateUserByEmailAndPhonenumber(User user) {
    	int resp = 0;
    	String statusMsg = null;
      try {
           resp = userRepository.updateUserByEmailAndPhonenumber(user.getPassword(),user.getEmail(),user.getPhoneNumber());
           statusMsg = user.getUsername() + "Account is saved successfully";
           if(logger.isDebugEnabled()) {
               logger.debug(statusMsg);
           }
           logger.info(statusMsg);
      }catch(Exception e) {
          statusMsg = user.getUsername() + " Account is failed to store a details";
          logger.error(e.getMessage());
          resp = 0;
      }
        return resp;
    }
    
    @Override
    public int updateUserStatusByEmailAndPhonenumber(User user) {
    	int resp = 0;
    	String statusMsg = null;
      try {
           resp = userRepository.updateUserStatusByEmailAndPhonenumber(user.getUserStatus() ,user.getEmail(),user.getPhoneNumber());
           statusMsg = user.getUsername() + "Account is saved successfully";
           if(logger.isDebugEnabled()) {
               logger.debug(statusMsg);
           }
           logger.info(statusMsg);
      }catch(Exception e) {
          statusMsg = user.getUsername() + " Account is failed to store a details";
          logger.error(e.getMessage());
          resp = 0;
      }
        return resp;
    }
    
    
    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public UserLoginResponse updateUser(RequestData requestDataReq , long userId) {
        User requestData = null;
        Restaurant rest = null;
        UserLoginResponse userResponse = new UserLoginResponse();
        requestData = userRepository.findByUserId(userId);
        User userResp = null;
        String statusMsg = null;
        try {
        if(requestData != null) {
            requestData.setPhoneNumber(requestDataReq.getPhoneNumber());        	
	    	requestData.setEmail(requestDataReq.getEmail());
	    	requestData.setUsername(requestDataReq.getUsername());
	    	requestData.setPassword(requestDataReq.getPassword());
	    	requestData.setLastModifiedDate(requestDataReq.getUserLoginDate());
	    	requestData.setUserLoginType(requestDataReq.getUserLoginType());
	    	requestData.setUserLastLoginType(requestDataReq.getUserLastLoginType());
	    	userResp = userRepository.save(requestData);
        } else {
        	logger.info("User doesn't exist for update"+requestDataReq.getPhoneNumber()+"--"+requestDataReq.getEmail());
        }
    	if(userResp != null) {
    		userResp.setPassword(null);
    		userResponse.setUser(userResp);
    	}
    	 if (requestDataReq.getUserRole().equals(UserRole.vendor) || requestDataReq.getUserRole().equals(UserRole.employee)) {
             rest = restaurantRepository.findByRestaurantId(requestDataReq.getRestaurantId());
             if(rest != null) {
	             rest.setEmail(requestDataReq.getEmail());
	             rest.setPhoneNumber(requestDataReq.getPhoneNumber());
	             rest.setRestaurantName(requestDataReq.getRestaurantName());
	             rest.setRestaurantAddress(requestDataReq.getRestaurantAddress());
	             rest.setRestaurantLocation(requestDataReq.getRestaurantLocation());
	             Restaurant restResp = restaurantRepository.save(rest);
	             userResponse.setRestaurant(restResp);
             }
    	 }
    	 statusMsg = requestDataReq.getUsername() + "  Account is saved successfully";
         if(logger.isDebugEnabled()) {
             logger.debug(statusMsg);
         }
         logger.info(statusMsg);
    }catch(Exception e) {
        statusMsg = requestDataReq.getUsername() + " Account is failed to store a details";
        logger.error(e.getMessage());
        userResponse = null;
    }
        return userResponse;
    }
    
    @Override
    public User updateUserLoginStatus(User requestDataReq , long userId) {
    	User user = userRepository.findByUserId(userId);
    	user.setLastModifiedDate(requestDataReq.getUserLoginDate());
    	user.setUserLastLoginType(user.getUserLoginType());
    	user.setUserLoginType(requestDataReq.getUserLoginType());
    	return userRepository.save(user);
    }

}
