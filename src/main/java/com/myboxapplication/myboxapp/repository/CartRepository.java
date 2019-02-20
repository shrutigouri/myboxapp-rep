package com.myboxapplication.myboxapp.repository;

import com.myboxapplication.myboxapp.models.mysql.Cart;
import com.myboxapplication.myboxapp.models.mysql.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    Cart findByCartId(long cartId);

    List<Cart> findAll();
    
    List<Cart> findByCustomerOrderId(String customerOrderId);
     

}
