package com.myboxapplication.myboxapp.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myboxapplication.myboxapp.models.mysql.CustomerOrder;
import com.myboxapplication.myboxapp.models.mysql.CustomerOrderDetail;
import com.myboxapplication.myboxapp.models.mysql.Restaurant;
import com.myboxapplication.myboxapp.models.mysql.User;


@Repository
public interface CustomerOrderDetailRepository extends JpaRepository<CustomerOrderDetail,Long> {

    List<CustomerOrderDetail> findAll();

    CustomerOrderDetail findByOrderCartDetailId(long orderCartDetailId);
    
}


