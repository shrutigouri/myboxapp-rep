package com.myboxapplication.myboxapp.services;

import com.myboxapplication.myboxapp.codetype.OrderType;
import com.myboxapplication.myboxapp.models.OrderCartRequestData;
import com.myboxapplication.myboxapp.models.mysql.CustomerOrder;
import com.myboxapplication.myboxapp.models.mysql.CustomerOrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerOrderService {

    List<CustomerOrder> getAllCustomerOrder();

    CustomerOrder addCustomerOrder(OrderCartRequestData orderCartRequestData);
    
    CustomerOrder updateCustomerOrder(String customerOrderId, CustomerOrder customerOrder);
    
    void deleteByCustomerOrderId(String customerOrderId);

    CustomerOrder getCustomerOrderId(String customerOrderId);

    Page<CustomerOrder> getOrderByOrderType(OrderType orderType, Pageable pageable);


}

