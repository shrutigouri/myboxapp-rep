package com.myboxapplication.myboxapp.webservices;

import com.myboxapplication.myboxapp.codetype.FoodType;
import com.myboxapplication.myboxapp.models.mysql.Menu;
import com.myboxapplication.myboxapp.models.mysql.Restaurant;
import com.myboxapplication.myboxapp.repository.MenuRepository;
import com.myboxapplication.myboxapp.services.MenuService;
import com.myboxapplication.myboxapp.services.ResponseGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/menu")
public class MenuResource {
    @Autowired
    ResponseGenerator responseGenerator;

    @Autowired
    MenuService menuService;

    @Autowired
    MenuRepository menuRepository;

    @GetMapping("public")
    public ResponseEntity getAllMenu(Pageable pageable) {
        return ResponseEntity
                .ok(responseGenerator
                        .success(menuService.getAllMenu(pageable),"menu.list"));
    }

    @PostMapping("public")
    public ResponseEntity addMenu(@RequestBody Menu menu) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseGenerator
                        .success(menuService.addMenu(menu),"menu.added"));
    }

    @GetMapping("{id}/menu/public")
    public ResponseEntity getMenuByFoodItemId(@PathVariable("id") long foodItemId){
        return ResponseEntity
                .ok(responseGenerator
                        .success(menuService.getMenuByFoodItemId(foodItemId),"menu.found"));
    }

    @GetMapping("get/{id}/public")
    public ResponseEntity getMenuByRestaurantId(@PathVariable("id") Restaurant restaurant){
        return ResponseEntity
                .ok(responseGenerator
                        .success(menuService.getMenuByRestaurant(restaurant),"menu.found"));
    }



    @PutMapping("update/{id}/public")
    public ResponseEntity updateMenu(@RequestBody Menu menu,@PathVariable("id") long foodItemId){
        return ResponseEntity
                .ok(responseGenerator
                        .success(menuService.updateMenu(foodItemId,menu),"menu.updated"));
    }

    @PostMapping("{id}/image/public")
    public ResponseEntity uploadMenuImage(@RequestParam("file") MultipartFile file, @PathVariable("id") long foodItemId){
        return ResponseEntity
                .ok(responseGenerator
                        .success(menuService.updateMenuImage(file, foodItemId),"menu.updated"));
    }

    @GetMapping("foodtype/public")
    public ResponseEntity getMenuByFoodType(@RequestParam(value = "foodType", required = false) FoodType foodType) {
        return ResponseEntity
                .ok(responseGenerator.success(menuService.getMenuByFoodtype(foodType),"types.menu"));
    }

    @DeleteMapping("delete/{id}/public")
    public ResponseEntity deleteMenuByFoodItemId(@PathVariable("id") long foodItemId){
        menuService.deleteMenuByFoodItemId(foodItemId);
       return ResponseEntity
                .ok(responseGenerator.success("Menu deleted successfully","menu.deleted"));
    }


}
