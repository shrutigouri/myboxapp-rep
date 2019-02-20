package com.myboxapplication.myboxapp.services;

import com.myboxapplication.myboxapp.models.OrderCartRequestData;
import com.myboxapplication.myboxapp.models.mysql.CustomerOrder;
import com.myboxapplication.myboxapp.models.mysql.CustomerOrderDetail;

import java.util.List;

public interface CustomerOrderService {

    List<CustomerOrder> getAllCustomerOrder();

    CustomerOrder addCustomerOrder(OrderCartRequestData orderCartRequestData);
    
    CustomerOrder updateCustomerOrder(String customerOrderId, CustomerOrder customerOrder);
    
    CustomerOrder deleteCustomerOrder(String customerOrderId, CustomerOrder customerOrder);

    CustomerOrder getCustomerOrderId(String customerOrderId);
    
}

