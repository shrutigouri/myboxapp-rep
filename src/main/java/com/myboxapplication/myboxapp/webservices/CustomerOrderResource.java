package com.myboxapplication.myboxapp.webservices;

import com.myboxapplication.myboxapp.codetype.OrderType;
import com.myboxapplication.myboxapp.models.OrderCartRequestData;
import com.myboxapplication.myboxapp.models.mysql.CustomerOrder;
import com.myboxapplication.myboxapp.services.CustomerOrderService;
import com.myboxapplication.myboxapp.services.ResponseGenerator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customerOrder")
public class CustomerOrderResource {

    @Autowired
    ResponseGenerator responseGenerator;

    @Autowired
    CustomerOrderService customerOrderService;

    @GetMapping("getAll/public")
    public ResponseEntity getAllCustOrder() {
        List<CustomerOrder> customerOrder = customerOrderService.getAllCustomerOrder();
        if (!customerOrder.isEmpty() && customerOrder != null)
            return ResponseEntity
                    .ok(responseGenerator
                            .success(customerOrder, "custorder.list"));
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("get/{id}/public")
    public ResponseEntity getCustomerOrderId(@PathVariable("id") String customerOrderId) {
        CustomerOrder customerOrder = customerOrderService.getCustomerOrderId(customerOrderId);
        if (customerOrder != null)
            return ResponseEntity
                    .ok(responseGenerator
                            .success(customerOrder, "custorder.found"));
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("add/public")
    public ResponseEntity addCustomerOrder(@RequestBody OrderCartRequestData orderCartRequestData) {
        CustomerOrder CustomerOrderAdd = customerOrderService.addCustomerOrder(orderCartRequestData);
        if (CustomerOrderAdd != null)
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(responseGenerator
                            .success(CustomerOrderAdd, "custorder.added"));
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("update/{id}/public")
    public ResponseEntity updateCustomerOrder(@PathVariable("id") String customerOrderId, @RequestBody CustomerOrder customerOrder) {
        CustomerOrder CustomerOrderUpd = customerOrderService.updateCustomerOrder(customerOrderId, customerOrder);
        if (CustomerOrderUpd != null)
            return ResponseEntity
                    .ok(responseGenerator
                            .success(CustomerOrderUpd, "custorder.updated"));
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
   /* @DeleteMapping("delete/{id}/public")
    public ResponseEntity deleteCustomerOrder(@PathVariable("id") String customerOrderId ){
       customerOrderService.deleteByCustomerOrderId(customerOrderId);
    	if(CustomerOrderDel != null)
    	return ResponseEntity
                .ok(responseGenerator
                        .success(customerOrderService.deleteByCustomerOrderId(customerOrderId),"custorder.updated"));
    	else
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/


    @DeleteMapping(value = "/delete/{id}/public")
    public ResponseEntity delete(@PathVariable("id") String customerOrderId) {
        customerOrderService.deleteByCustomerOrderId(customerOrderId);
        if (customerOrderId != null)
            return ResponseEntity
                    .ok(responseGenerator
                            .success("Customer Order Deleted successfully", "custorder.deleted"));
        else

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    @GetMapping("orderType/public")
    public ResponseEntity getOrderByOrderType(@RequestParam(value = "orderType", required = false) OrderType orderType, Pageable pageable) {
        return ResponseEntity
                .ok(responseGenerator.success(customerOrderService.getOrderByOrderType(orderType,pageable),"types.order"));
    }
}

