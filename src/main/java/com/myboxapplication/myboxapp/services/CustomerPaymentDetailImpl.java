package com.myboxapplication.myboxapp.services;

import com.myboxapplication.myboxapp.codetype.PaymentMethod;
import com.myboxapplication.myboxapp.codetype.PaymentStatus;
import com.myboxapplication.myboxapp.exceptions.CustomerPaymentDetailNotFoundException;
import com.myboxapplication.myboxapp.models.mysql.CustomerOrder;
import com.myboxapplication.myboxapp.models.mysql.CustomerPaymentDetail;
import com.myboxapplication.myboxapp.repository.CustomerPaymentDetailRepository;
import com.myboxapplication.myboxapp.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerPaymentDetailImpl implements CustomerPaymentDetailService {

    @Autowired
    CustomerPaymentDetailRepository customerPaymentDetailRepository;

    @Autowired
    CustomerPaymentDetailService customerPaymentDetailService;

    @Autowired
    CustomerOrderRepository customerOrderRepository;

    @Override
    public List<CustomerPaymentDetail> getAllCustomerPaymentDetail() {
        return customerPaymentDetailRepository.findAll();
    }

    @Override
    public CustomerPaymentDetail addCustomerPaymentDetail(CustomerPaymentDetail customerPaymentDetail) {
        customerPaymentDetail.setPaymentMethod(PaymentMethod.cashinhand);
        customerPaymentDetail.setPaymentStatus(PaymentStatus.inprogress);
        return customerPaymentDetailRepository.save(customerPaymentDetail);
    }

    @Override
    public CustomerPaymentDetail updateCustomerPaymentDetail(long paymentId, CustomerPaymentDetail customerPaymentDetail) {

        CustomerPaymentDetail customerPaymentDetail1 = customerPaymentDetailRepository.findByPaymentId(paymentId);

        if (customerPaymentDetail1==null){

            throw new CustomerPaymentDetailNotFoundException("CustomerPaymentDetail not Found= " +paymentId);
        }

        customerPaymentDetail1.setPaymentMethod(customerPaymentDetail.getPaymentMethod());
        customerPaymentDetail1.setCardToken(customerPaymentDetail.getCardToken());
        customerPaymentDetail1.setPaymentStatus(customerPaymentDetail.getPaymentStatus());
        customerPaymentDetail1.setTransactionRefNum(customerPaymentDetail.getTransactionRefNum());
        customerPaymentDetail1.setPaymentDate(customerPaymentDetail.getPaymentDate());

        return customerPaymentDetailRepository.save(customerPaymentDetail1);

    }


    @Override
    public CustomerPaymentDetail getCustomerPaymentDetailById(long paymentId) {
        return customerPaymentDetailRepository.findByPaymentId(paymentId);
    }
    
}
