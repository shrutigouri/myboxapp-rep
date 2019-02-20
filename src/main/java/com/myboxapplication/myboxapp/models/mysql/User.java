package com.myboxapplication.myboxapp.models.mysql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.myboxapplication.myboxapp.codetype.LoginType;
import com.myboxapplication.myboxapp.codetype.UserRole;
import com.myboxapplication.myboxapp.codetype.UserStatus;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable {
    @Id
    @Column (name = "user_id")
    @Value("${userId:0}")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long userId;

    @NotEmpty(message = "Please provide phonenumber")
    //@Pattern(regexp="(^$|[0-9]{10})")
    @Column(nullable = false,name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(nullable = false,name = "email", unique = true)
    @Email(message = "Please provide a valid e-mail")
    @NotEmpty(message = "Please provide an e-mail")
    private String email;

    @Column(nullable = false,name = "user_name")
    @NotEmpty(message = "Please provide username")
    private String username;

    @Column(name = "user_pwd")
    @JsonProperty
    @NotEmpty(message = "Please provide password") //nullable = false,
    private String password;

    @Column(name = "user_role",nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(name = "user_creation_date")
    @CreatedDate
    private String userCreationDate = new Date().toString();

    @Column(nullable = false, name = "user_last_modified_date")
    @LastModifiedDate
    private String lastModifiedDate;

    @Column(name = "user_login_date")
    private String userLoginDate;

    @Column(name = "user_login_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private LoginType userLoginType;

    @Column(name = "user_last_login_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private LoginType userLastLoginType;

    @Column(name = "user_status")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getUserCreationDate() {
        return userCreationDate;
    }

    public void setUserCreationDate(String userCreationDate) {
        this.userCreationDate = userCreationDate;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getUserLoginDate() {
        return userLoginDate;
    }

    public void setUserLoginDate(String userLoginDate) {
        this.userLoginDate = userLoginDate;
    }

    public LoginType getUserLoginType() {
        return userLoginType;
    }

    public void setUserLoginType(LoginType userLoginType) {
        this.userLoginType = userLoginType;
    }

    public LoginType getUserLastLoginType() {
        return userLastLoginType;
    }

    public void setUserLastLoginType(LoginType userLastLoginType) {
        this.userLastLoginType = userLastLoginType;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

}
