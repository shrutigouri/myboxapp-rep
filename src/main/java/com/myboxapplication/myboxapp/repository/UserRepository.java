package com.myboxapplication.myboxapp.repository;

import com.myboxapplication.myboxapp.codetype.UserStatus;
import com.myboxapplication.myboxapp.models.mysql.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Page<User> findAll(Pageable pageable);

    User findUserByPhoneNumber(String phoneNumber);

    User findUserByEmail(String email);

       
    //@Query("select u from User u where u.email = :email and u.phoneNumber = :phoneNumber")
  //  User findUserByEmailAndPhoneNumber(@Param("email") String email,@Param("phoneNumber") String phoneNumber);
    
    User findByUsername(String username);

    User findUserByEmailAndPhoneNumber(String email,String phoneNumber);

    User findByUserId(long userId);

    @Modifying
    @Transactional(readOnly=false)
    @Query("update User u set u.password = :password where u.email = :email and u.phoneNumber = :phoneNumber ")
    int updateUserByEmailAndPhonenumber(@Param("password") String password, @Param("email") String email, @Param("phoneNumber")  String phoneNumber);

    @Modifying
    @Transactional(readOnly=false)
    @Query("update User u set u.userStatus = :userStatus where u.email = :email and u.phoneNumber = :phoneNumber ")
    int updateUserStatusByEmailAndPhonenumber(@Param("userStatus") UserStatus userStatus, @Param("email") String email, @Param("phoneNumber")  String phoneNumber);



}
