package com.myboxapplication.myboxapp.exceptions;


public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String exception) {
        super(exception);
    }

}
