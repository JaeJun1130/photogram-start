package com.cos.photogramstart.handler.aop;

import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@Aspect
public class ValidationAdvice {

    //해당 경로에 컨트롤러에 모든메소드에 그메소드에 파라미터가모든 상관없는것
    //proceedingJoinPoint 메소드의 모든정보에 접근할 수 있다.
    //proceed 그함수도 다시 돌아가라.
    @Around("execution(* com.cos.photogramstart.web.api.*Controller.*(..))")
    public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Web Api Controller==============");

        //메소드 메개변수의 접급
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                System.out.println("유효성 검사를 하는 메소드");
                BindingResult bindingResult = (BindingResult) arg;

                if (bindingResult.hasErrors()) {
                    Map<String, String> errorMap = new HashMap<>();

                    for (FieldError fieldError : bindingResult.getFieldErrors()) {
                        errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                        System.out.println("fieldError.getDefaultMessage() = " + fieldError.getDefaultMessage());
                    }
                    throw new CustomValidationApiException("유효성 검사 실패", errorMap);
                }
            }
            System.out.println("arg = " + arg);
        }
        return proceedingJoinPoint.proceed();
    }

    @Around("execution(* com.cos.photogramstart.web.*Controller.*(..))")
    public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Web Controller==============");
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                System.out.println("유효성 검사를 하는 메소드");
                //@Valid 에러가 발생시 BindingResult 에러 적재
                BindingResult bindingResult = (BindingResult) arg;
                if (bindingResult.hasErrors()) {
                    Map<String, String> errorMap = new HashMap<>();

                    for (FieldError fieldError : bindingResult.getFieldErrors()) {
                        errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                        System.out.println("fieldError.getDefaultMessage() = " + fieldError.getDefaultMessage());
                    }
                    throw new CustomValidationException("유효성 검사 실패", errorMap);
                }
            }
            System.out.println("arg = " + arg);
        }
        return proceedingJoinPoint.proceed();
    }
}
