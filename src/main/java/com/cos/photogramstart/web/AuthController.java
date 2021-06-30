package com.cos.photogramstart.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    /**
     * 로그인 페이지 진입
     * @return
     */
    @GetMapping("/auth/signin")
    public String signinPage() {
        return "auth/signin";
    }

    /**
     * 회원가입 페이지 진입
     * @return
     */
    @GetMapping("/auth/signup")
    public String signupPage() {
        return "auth/signup";
    }

    /**
     * 회원가입 요청
     * @return
     */
    @PostMapping("/auth/signin")
    public String signup() {
        System.out.println("가입");
        return "auth/signin";
    }
}
