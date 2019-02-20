package com.myboxapplication.myboxapp.repository;

import com.myboxapplication.myboxapp.models.mysql.CustomerPaymentDetail;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerPaymentDetailRepository extends JpaRepository<CustomerPaymentDetail,Long> {

    Page<CustomerPaymentDetail> findAll(Pageable pageable);

    CustomerPaymentDetail findByPaymentId(long paymentId);
    
}
