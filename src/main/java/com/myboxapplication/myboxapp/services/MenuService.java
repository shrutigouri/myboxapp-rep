package com.myboxapplication.myboxapp.services;

import com.myboxapplication.myboxapp.codetype.FoodType;
import com.myboxapplication.myboxapp.models.mysql.Menu;
import com.myboxapplication.myboxapp.models.mysql.Restaurant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MenuService {

    Page<Menu> getAllMenu(Pageable pageable);

    Menu addMenu(Menu menu);

    Menu updateMenu(long foodItemId, Menu menu);

    Menu getMenuByFoodItemId(long foodItemId);
    
    List<Menu> getMenuByRestaurantId(long restaurantId);

    String updateMenuImage(MultipartFile file, long menuId);

    List<Menu> getMenuByFoodtype(FoodType foodItemType);

    void deleteMenuByFoodItemId(long foodItemId,Menu menu);
}
