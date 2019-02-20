package com.myboxapplication.myboxapp.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    private Object data;
    private String message;
    private LocalDateTime timestamp;
    private String debug;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponse(Object data, String message) {
        this();
        this.data = data;
        this.message = message;
    }

    public ApiResponse(Object data, String message, String debug) {
        this();
        this.data = data;
        this.message = message;
        this.debug = debug;
    }

    @Override
    public String toString() {
        return "{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", debug='" + debug + '\'' +
                '}';
    }
}
