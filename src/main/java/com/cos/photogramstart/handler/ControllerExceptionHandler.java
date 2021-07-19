package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.utils.Script;
import com.cos.photogramstart.web.dto.CMResDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public String validationsException(CustomValidationException e) {
        return Script.back(e.getErrorMap().toString());
//        return new CMResDto<Map<String,String>>(-1, e.getMessage(),e.getErrorMap());
    }

}
