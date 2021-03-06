package com.myboxapplication.myboxapp.services;

import com.myboxapplication.myboxapp.codetype.*;
import com.myboxapplication.myboxapp.exceptions.CustOrderNotFoundException;
import com.myboxapplication.myboxapp.models.mysql.CustomerOrderDetail;
import com.myboxapplication.myboxapp.models.mysql.CustomerPaymentDetail;
import com.myboxapplication.myboxapp.repository.CustomerOrderRepository;
import com.myboxapplication.myboxapp.models.OrderCartRequestData;
import com.myboxapplication.myboxapp.models.mysql.CustomerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CustomerOrderImpl implements CustomerOrderService {

    @Autowired
    CustomerOrderRepository customerOrderRepository;

    @Autowired
    CustomerOrderService customerOrderService;
    
    @Autowired
    CustomerPaymentDetailService customerPaymentDetailService;

    @Override
    public List<CustomerOrder> getAllCustomerOrder() {
        return customerOrderRepository.findAll();
    }

    @Override
    public CustomerOrder addCustomerOrder(OrderCartRequestData orderCartRequestData) {
    	 CustomerPaymentDetail  customerPaymentDetail = new CustomerPaymentDetail();
		 customerPaymentDetail.setPaymentDate(orderCartRequestData.getOrderDate());
		 customerPaymentDetail.setPaymentMethod(PaymentMethod.cashinhand);
		 customerPaymentDetail.setPaymentStatus(PaymentStatus.inprogress);
		 CustomerPaymentDetail customerPaymentDetail1 = customerPaymentDetailService.addCustomerPaymentDetail(customerPaymentDetail);		    

		String custOrderId = UUID.randomUUID().toString().replace("-", "");
    	CustomerOrder customerOrder1 = new CustomerOrder();
    	customerOrder1.setCustomerOrderId(custOrderId);
    	customerOrder1.setCustOrderDate(orderCartRequestData.getOrderDate());
	    customerOrder1.setCustRatingStatus(null);
	    customerOrder1.setCustRating(4.5);
	    customerOrder1.setDeliveryFoodStatus(DeliveryFoodStatus.NotPlaced);
	    customerOrder1.setOrderOverallStatus(OrderOverallStatus.inprogress);
	    customerOrder1.setOrderProgressComment(null);

        return customerOrderRepository.save(customerOrder1);
    }

    @Override
    public CustomerOrder updateCustomerOrder(String customerOrderId, CustomerOrder customerOrder) {
        CustomerOrder customerOrder1 = customerOrderRepository.findByCustomerOrderId(customerOrderId);

        if (customerOrder1==null){

            throw new CustOrderNotFoundException("CustomerOrder not Found= " +customerOrderId);
        }
        customerOrder1.setCustOrderDate(customerOrder.getCustOrderDate());
        customerOrder1.setDeliveryFoodStatus(DeliveryFoodStatus.Onhand);
        customerOrder1.setOrderOverallStatus(OrderOverallStatus.success);
        customerOrder1.setCustRatingStatus(CustRatingStatus.rated);
        customerOrder1.setCustRating(customerOrder.getCustRating());
        customerOrder1.setDeliveryFoodStatus(DeliveryFoodStatus.NotPlaced);
        customerOrder1.setOrderOverallStatus(OrderOverallStatus.inprogress);
        customerOrder1.setOrderProgressComment(customerOrder.getOrderProgressComment());

        return customerOrderRepository.save(customerOrder1);
    }

    @Override
    public CustomerOrder getCustomerOrderId(String customerOrderId) {
        return customerOrderRepository.findByCustomerOrderId(customerOrderId);
    }


    @Override
    public void deleteByCustomerOrderId(String customerOrderId) {

        CustomerOrder customerOrder1 = customerOrderRepository.findByCustomerOrderId(customerOrderId);

        if (customerOrder1==null){

            throw new CustOrderNotFoundException("CustomerOrder not Found= " +customerOrderId);
        }

        customerOrderRepository.deleteByCustomerOrderId(customerOrderId);
    }

}

