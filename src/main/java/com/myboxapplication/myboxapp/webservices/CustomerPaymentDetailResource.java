package com.myboxapplication.myboxapp.webservices;

import com.myboxapplication.myboxapp.models.OrderCartDetailResonse;
import com.myboxapplication.myboxapp.models.mysql.CustomerPaymentDetail;
import com.myboxapplication.myboxapp.services.CustomerPaymentDetailService;
import com.myboxapplication.myboxapp.services.ResponseGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paymentdetails")
public class CustomerPaymentDetailResource {

    @Autowired
    ResponseGenerator responseGenerator;

    @Autowired
    CustomerPaymentDetailService customerPaymentDetailService;

    @GetMapping("public")
    public ResponseEntity getAllCustPaymentDetail(Pageable pageable){
        return ResponseEntity
                .ok(responseGenerator
                        .success(customerPaymentDetailService.getAllCustomerPaymentDetail(pageable),"paymentdetail.list"));
    }

    @GetMapping("{id}/paymentdetail/public")
    public ResponseEntity getCustomerPaymentDetailById(@PathVariable("id") long paymentId){
    	CustomerPaymentDetail customerPaymentDetail = customerPaymentDetailService.getCustomerPaymentDetailById(paymentId);
    	if (customerPaymentDetail != null)
    	return ResponseEntity
                .ok(responseGenerator
                        .success(customerPaymentDetail,"paymentdetail.found"));
    	 else
        	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    		
    }

    @PostMapping("public")
    public ResponseEntity addCustPaymentDetail(@RequestBody CustomerPaymentDetail customerPaymentDetail){
     return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseGenerator
                        .success(customerPaymentDetailService.addCustomerPaymentDetail(customerPaymentDetail),"paymentdetail.added"));
    }

    @PutMapping("update/{id}/public")
    public ResponseEntity updateCustomerPaymentDetail(@PathVariable("id") long paymentId ,@RequestBody CustomerPaymentDetail customerPaymentDetail){
    	
    	return ResponseEntity
                .ok(responseGenerator
                        .success(customerPaymentDetailService.updateCustomerPaymentDetail(paymentId,customerPaymentDetail),"paymentdetail.updated"));
    }

}
