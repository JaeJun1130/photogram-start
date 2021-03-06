package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.utils.Script;
import com.cos.photogramstart.web.dto.CMResDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public String validationsException(CustomValidationException e) {
        if(e.getErrorMap() == null){
            return Script.back(e.getMessage());
        }else{
             return Script.back(e.getErrorMap().toString());
        }
    }

    @ExceptionHandler(CustomException.class)
    public String exception(CustomException e) {
        return Script.back(e.getMessage());
    }
//    @ExceptionHandler(CustomValidationApiException.class)
//    public CMResDto<?> CustomValidationApiException(CustomValidationApiException e) {
//        return new CMResDto<>(-1, e.getMessage(),e.getErrorMap());
//    }

    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<?> CustomValidationApiException(CustomValidationApiException e) {
        return new ResponseEntity<>(new CMResDto<>(-1,e.getMessage(),e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> CustomApiException(CustomApiException e) {
        return new ResponseEntity<>(new CMResDto<>(-1,e.getMessage(),null), HttpStatus.BAD_REQUEST);
    }

}
