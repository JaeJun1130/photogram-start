package com.cos.photogramstart.web;

import com.cos.photogramstart.web.dto.auth.SignupDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
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
    @PostMapping("/auth/signup")
    public String signup(SignupDto signupDto) {
        log.info("signupDto : {}",signupDto.toString());
        return "auth/signin";
    }
}
