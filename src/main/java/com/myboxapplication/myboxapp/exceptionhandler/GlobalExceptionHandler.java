package com.myboxapplication.myboxapp.exceptionhandler;

import com.myboxapplication.myboxapp.exceptions.RecordNotFoundException;
import com.myboxapplication.myboxapp.models.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.myboxapplication.myboxapp.services.ResponseGenerator;
import org.springframework.beans.factory.annotation.Autowired;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
      

    @Autowired
    ResponseGenerator responseGenerator;
    
    @ExceptionHandler(Exception.class)
    @RequestMapping(produces = "application/json")
    public final ResponseEntity<ApiResponse> handleAllExceptions(Exception ex, HttpServletRequest  request) {
    	System.out.println("==================="+ex);
     	logger.error(ex.getLocalizedMessage());
 
        ApiResponse error = new ApiResponse("Internal Server Error", ex.getLocalizedMessage());      
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(responseGenerator.error(error, "internal.server.error"));
        
    }
 
    @ExceptionHandler(RecordNotFoundException.class)
    @RequestMapping(produces = "application/json")
    public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
    	System.out.println("==================="+ex);
     	logger.error(ex.getLocalizedMessage());

        ApiResponse error = new ApiResponse("Record Not Found", ex.getLocalizedMessage());
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
 
    @Override
    @RequestMapping(produces = "application/json")
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        System.out.println("==================="+ex);
     	logger.error(ex.getStackTrace().toString());

        ApiResponse error = new ApiResponse("Validation Failed", details.toString());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
