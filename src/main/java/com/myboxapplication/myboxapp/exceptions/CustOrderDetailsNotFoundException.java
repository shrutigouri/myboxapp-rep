package com.myboxapplication.myboxapp.exceptions;

//exception class
public class CustOrderDetailsNotFoundException extends RuntimeException {
    public CustOrderDetailsNotFoundException(String exception) {
        super(exception);
    }
}
