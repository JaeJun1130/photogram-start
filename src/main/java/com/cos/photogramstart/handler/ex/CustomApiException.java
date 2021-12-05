package com.cos.photogramstart.handler.ex;

import lombok.Data;

import java.util.Map;

@Data
public class CustomApiException extends RuntimeException{
    //객체를 구분할떄
    private static final long SerialVersionUID = 1L;

    public CustomApiException(String message) {
        super(message);
    }
}
