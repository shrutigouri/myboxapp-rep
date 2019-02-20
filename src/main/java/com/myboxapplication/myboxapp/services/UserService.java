package com.myboxapplication.myboxapp.services;

import com.myboxapplication.myboxapp.codetype.UserStatus;
import com.myboxapplication.myboxapp.models.UserLoginResponse;
import com.myboxapplication.myboxapp.models.mysql.RequestData;
import com.myboxapplication.myboxapp.models.mysql.Restaurant;
import com.myboxapplication.myboxapp.models.mysql.User;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    List<User> getAllUsers();

    UserLoginResponse updateUser(RequestData requestData, long userId);

    User getUserByEmail(String email);

    User getUserByPhoneNumber(String phoneNumber);

    User getUserByUserId(long userId);

    User AddUserLoginDetail(RequestData userData);
    
    Restaurant AddVendorUserLoginDetail(RequestData userData, User user);
        
    User getUserByEmailAndPhoneNumber(String email,String phoneNumber);

    int updateUserByEmailAndPhonenumber(User user);
    
    int updateUserStatusByEmailAndPhonenumber(User user);
    
    User updateUserLoginStatus(User user , long userId);

}

