package com.cos.photogramstart.handler.ex;

import lombok.Data;

import java.util.Map;

@Data
public class CustomValidationException extends RuntimeException{

    //객체를 구분할떄
    private static final long SerialVersionUID = 1L;

//    private String message;
    private Map<String,String> errorMap;

    public CustomValidationException(String message, Map<String, String> errorMap) {
//        this.message = message;
        super(message);
        this.errorMap = errorMap;
    }
}
