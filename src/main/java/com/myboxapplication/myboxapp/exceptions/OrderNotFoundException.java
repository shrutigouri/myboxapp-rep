package com.myboxapplication.myboxapp.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String exception) {
        super(exception);
    }

}
