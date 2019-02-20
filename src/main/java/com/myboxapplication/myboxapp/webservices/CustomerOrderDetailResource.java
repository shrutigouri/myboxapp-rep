package com.myboxapplication.myboxapp.webservices;

import com.myboxapplication.myboxapp.codetype.PaymentMethod;
import com.myboxapplication.myboxapp.codetype.PaymentStatus;
import com.myboxapplication.myboxapp.models.OrderCartDetailResonse;
import com.myboxapplication.myboxapp.models.OrderCartRequestData;
import com.myboxapplication.myboxapp.models.mysql.CustomerOrder;
import com.myboxapplication.myboxapp.models.mysql.CustomerOrderDetail;
import com.myboxapplication.myboxapp.models.mysql.CustomerPaymentDetail;
import com.myboxapplication.myboxapp.models.mysql.Restaurant;
import com.myboxapplication.myboxapp.models.mysql.User;
import com.myboxapplication.myboxapp.services.CustomerOrderDetailService;
import com.myboxapplication.myboxapp.services.CustomerOrderService;
import com.myboxapplication.myboxapp.services.CustomerPaymentDetailService;
import com.myboxapplication.myboxapp.services.ResponseGenerator;
import com.myboxapplication.myboxapp.services.RestaurentService;
import com.myboxapplication.myboxapp.services.UserService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customerOrderDetail")
public class CustomerOrderDetailResource {

    @Autowired
    ResponseGenerator responseGenerator;

    @Autowired
    CustomerOrderDetailService customerOrderDetailService;

    @GetMapping("/getAll/public")
    public ResponseEntity getCustomerOrderDetail() {
    	List<CustomerOrderDetail> customerOrderDetail = customerOrderDetailService.getAllCustomerOrderDetail();
    	if(customerOrderDetail != null)
        return ResponseEntity
                .ok(responseGenerator
                        .success(customerOrderDetail,"customer.order.list"));
        else
        	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}

    @PostMapping("add/public")
    public ResponseEntity addCustomerOrderDetail(@RequestBody OrderCartRequestData orderCartRequestData) {
    	OrderCartDetailResonse  customerOrderDtl =customerOrderDetailService.addCustomerOrderDetail(orderCartRequestData);
    		if(customerOrderDtl != null)
    			return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseGenerator
                        .success(customerOrderDtl,"customer.order.added"));
    		else 
    			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("get/{id}/public")
    public ResponseEntity getCustomerOrderDetailById(@PathVariable("id") long orderCartDetailId){
    	CustomerOrderDetail customerOrderDetail =customerOrderDetailService.getCustomerOrderDetailByOrderCartDetailId(orderCartDetailId);
    	if(customerOrderDetail != null)
        return ResponseEntity
                .ok(responseGenerator
                        .success(customerOrderDetail,"customer.order.found"));
    	 else 
        	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("update/{id}/public")
    public ResponseEntity updateCustomerOrderDetail(@RequestBody CustomerOrderDetail customerOrderDetail,@PathVariable("id") long orderCartDetailId){
    	CustomerOrderDetail customerOrderDetails=customerOrderDetailService.updateCustomerOrderDetail(orderCartDetailId,customerOrderDetail);
    	if(customerOrderDetails != null)
    	return ResponseEntity
                .ok(responseGenerator
                        .success(customerOrderDetails,"customer.order.updated"));
    	else 
       	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("delete/{id}/public")
    public ResponseEntity deleteCustomerOrderDetail(@RequestBody CustomerOrderDetail customerOrderDetail,@PathVariable("id") long orderCartDetailId){
    	CustomerOrderDetail customerOrderDetails = customerOrderDetailService.deleteCustomerOrderDetail(orderCartDetailId,customerOrderDetail);
    	if(customerOrderDetails != null)
    	return ResponseEntity
                .ok(responseGenerator
                        .success(customerOrderDetails,"customer.order.updated"));
    	else 
          	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
