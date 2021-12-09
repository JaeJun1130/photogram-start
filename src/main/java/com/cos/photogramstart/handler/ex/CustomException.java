package com.cos.photogramstart.handler.ex;

import lombok.Data;

import java.util.Map;

@Data
public class CustomException extends RuntimeException{

    //객체를 구분할떄
    private static final long SerialVersionUID = 1L;

    public CustomException(String message) {
        super(message);
    }
}
