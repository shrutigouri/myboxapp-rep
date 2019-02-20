package com.myboxapplication.myboxapp.repository;

import com.myboxapplication.myboxapp.codetype.OrderType;
import com.myboxapplication.myboxapp.models.mysql.CustomerOrder;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder,Long> {


    
    CustomerOrder  findByCustomerOrderId(String customerOrderId);
    
    List<CustomerOrder> findAll();

    void deleteByCustomerOrderId(String customerOrderId);

    Page<CustomerOrder> findOrderByOrderType(OrderType orderType, Pageable pageable);
}

