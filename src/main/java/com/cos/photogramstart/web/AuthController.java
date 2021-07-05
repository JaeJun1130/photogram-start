package com.cos.photogramstart.web;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//@RequiredArgsConstructor //final 필드를 Di할때 사용
@Controller
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

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

        User user = signupDto.toEntity();
        log.info("user : {} ",user.toString());

        //Jpa 인설트
        User userEntity = authService.회원가입(user);
        System.out.println(userEntity);
        return "auth/signin";
    }
}
