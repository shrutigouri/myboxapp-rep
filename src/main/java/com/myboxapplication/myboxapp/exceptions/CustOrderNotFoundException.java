package com.myboxapplication.myboxapp.exceptions;

public class CustOrderNotFoundException extends RuntimeException {
    public CustOrderNotFoundException(String exception) {
            super(exception);
        }
}
