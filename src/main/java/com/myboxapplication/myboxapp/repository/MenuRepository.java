package com.myboxapplication.myboxapp.repository;

import com.myboxapplication.myboxapp.codetype.FoodType;
import com.myboxapplication.myboxapp.models.mysql.Menu;
import com.myboxapplication.myboxapp.models.mysql.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {

    Menu findMenuByFoodItemId(long foodItemId);

    List<Menu> findMenuByFoodType(FoodType foodType);

    @Query("SELECT m FROM Menu m where m.restaurant =:restaurant")
    List<Menu> getMenuByRestaurant(@Param("restaurant") Restaurant restaurant);

    void deleteMenuByFoodItemId(long foodItemId);

    Page<Menu> findAll(Pageable pageable);

}
