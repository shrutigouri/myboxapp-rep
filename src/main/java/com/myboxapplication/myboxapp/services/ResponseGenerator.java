package com.myboxapplication.myboxapp.services;

import com.myboxapplication.myboxapp.models.ApiResponse;
import com.myboxapplication.myboxapp.utils.ErrorMessageReader;
import com.myboxapplication.myboxapp.utils.ResponseMessageReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseGenerator {

    @Autowired
    ErrorMessageReader errorMessageReader;

    @Autowired
    ResponseMessageReader responseMessageReader;

    public ApiResponse success(Object data, String messageKey){
        return new ApiResponse(data, responseMessageReader.getProperty(messageKey));
    }

    public ApiResponse success(Object data, String messageKey, List<Object> args){
        return new ApiResponse(data, responseMessageReader.getProperty(messageKey,args));
    }

    public ApiResponse error(String messageKey, String stackTrace){
        return new ApiResponse(null, errorMessageReader.getProperty(messageKey), stackTrace);
    }
    
    public ApiResponse error(Object data, String messageKey){
        return new ApiResponse(data, errorMessageReader.getProperty(messageKey));
    }

    public ApiResponse error(String messageKey, List<Object> args, String stackTrace){
        return new ApiResponse(null, errorMessageReader.getProperty(messageKey,args),stackTrace);
    }
}
