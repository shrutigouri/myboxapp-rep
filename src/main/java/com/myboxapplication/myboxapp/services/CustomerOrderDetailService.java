package com.myboxapplication.myboxapp.services;

import java.util.List;

import com.myboxapplication.myboxapp.models.OrderCartDetailResonse;
import com.myboxapplication.myboxapp.models.OrderCartRequestData;
import com.myboxapplication.myboxapp.models.mysql.CustomerOrderDetail;

public interface CustomerOrderDetailService {
	

    List<CustomerOrderDetail> getAllCustomerOrderDetail();

    OrderCartDetailResonse addCustomerOrderDetail(OrderCartRequestData orderCartRequestData); 

    CustomerOrderDetail updateCustomerOrderDetail(long orderCartDetailId, CustomerOrderDetail customerOrderDetail);

    CustomerOrderDetail deleteCustomerOrderDetail(long orderCartDetailId,CustomerOrderDetail customerOrderDetail);

    CustomerOrderDetail getCustomerOrderDetailByOrderCartDetailId(long orderCartDetailId);

  /*  CustomerOrderDetail getCustomerOrderDetailByCustomerOrderId(String customerOrderId);
    
    CustomerOrderDetail getCustomerOrderDetailByRestaurantId(long restaurantId);
    
    CustomerOrderDetail getCustomerOrderDetailByUserId(long userId); */


}
