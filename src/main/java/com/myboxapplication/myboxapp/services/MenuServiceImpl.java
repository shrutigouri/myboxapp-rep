package com.myboxapplication.myboxapp.services;

import com.myboxapplication.myboxapp.codetype.FoodItemStatus;
import com.myboxapplication.myboxapp.codetype.FoodType;
import com.myboxapplication.myboxapp.exceptions.MenuNotFoundException;
import com.myboxapplication.myboxapp.models.mysql.Menu;
import com.myboxapplication.myboxapp.models.mysql.Restaurant;
import com.myboxapplication.myboxapp.repository.MenuRepository;
import com.myboxapplication.myboxapp.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    FileStorageService fileStorageService;

    @Autowired
    ResponseGenerator responseGenerator;

    @Override
    public Page<Menu> getAllMenu(Pageable pageable) {
        return menuRepository.findAll(pageable);
    }

    @Override
    public Menu getMenuByFoodItemId(long foodItemId) {
        return menuRepository.findMenuByFoodItemId(foodItemId);
    }
    
    @Override
    public List<Menu> getMenuByRestaurant(Restaurant restaurant){
    	 return menuRepository.getMenuByRestaurant(restaurant);
    }

    @Override
    public Menu addMenu(Menu menu) {
        menu.setFoodItemStatus(FoodItemStatus.available);
        menu.setFoodType(menu.getFoodType());
        Restaurant restaurant = restaurantRepository.findByRestaurantId(menu.getRestaurant().getRestaurantId());
        menu.setRestaurant(restaurant);
        return menuRepository.save(menu);

    }

    @Override
    public Menu updateMenu(long foodItemId, Menu menu) {

        Menu menu1 = menuRepository.findMenuByFoodItemId(foodItemId);

        if (menu1==null){


           return (Menu) ResponseEntity
                   .status(HttpStatus.NO_CONTENT);


        }

        menu1.setFoodItemName(menu.getFoodItemName());
        menu1.setFoodItemDescription(menu.getFoodItemDescription());
        menu1.setFoodItemPrice(menu.getFoodItemPrice());
        menu1.setFoodType(menu.getFoodType());
        menu1.setFoodItemStatus(menu.getFoodItemStatus());
        menu1.setFoodImage(menu.getFoodImage());
        menu1.setFoodRating(menu.getFoodRating());

        return menuRepository.save(menu1);
    }

    public String updateMenuImage(MultipartFile file, long menuId){
        Menu menu = getMenuByFoodItemId(menuId);
        String fileName = fileStorageService.storeFile(file);
        menu.setFoodImage(fileName);
        menuRepository.save(menu);
        return fileName;
    }

    @Override
    public Page<Menu> getMenuByFoodtype(FoodType foodType ,Pageable pageable) {
        return menuRepository.findMenuByFoodType(foodType,pageable);
    }



    @Override
    public void deleteMenuByFoodItemId(long foodItemId) {

        Menu menu1 = menuRepository.findMenuByFoodItemId(foodItemId);

        if (menu1==null){

            throw new MenuNotFoundException("Menu not Found= " +foodItemId);
        }
        menuRepository.deleteMenuByFoodItemId(foodItemId);
    }

}
