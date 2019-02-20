package com.myboxapplication.myboxapp.repository;

import com.myboxapplication.myboxapp.models.mysql.CustomerOrder;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder,Long> {


    
    CustomerOrder  findByCustomerOrderId(String customerOrderId);
    
    List<CustomerOrder> findAll();

    void deleteByCustomerOrderId(String customerOrderId);
}

