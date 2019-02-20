package com.myboxapplication.myboxapp.services;

import com.myboxapplication.myboxapp.models.mysql.CustomerPaymentDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerPaymentDetailService {

    Page<CustomerPaymentDetail> getAllCustomerPaymentDetail(Pageable pageable);

    CustomerPaymentDetail addCustomerPaymentDetail(CustomerPaymentDetail customerPaymentDetail);

    CustomerPaymentDetail updateCustomerPaymentDetail(long paymentId, CustomerPaymentDetail customerPaymentDetails);

    CustomerPaymentDetail getCustomerPaymentDetailById(long paymentId);


}
