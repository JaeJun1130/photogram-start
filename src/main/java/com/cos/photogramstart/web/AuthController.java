package com.cos.photogramstart.web;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
//    @ResponseBody
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {
        //@Valid 에러가 발생시 BindingResult 에러 적재
        if(bindingResult.hasErrors()){
            Map<String,String> errorMap = new HashMap<>();

            for(FieldError fieldError : bindingResult.getFieldErrors()){
                errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
                System.out.println("fieldError.getDefaultMessage() = " + fieldError.getDefaultMessage());
            }
            throw new CustomValidationException("유효성 검사 실패",errorMap);
        }else {
            User user = signupDto.toEntity(); //사용자가 요청한 값을 Vo로 받은후 Build를 통해 Entity에 셋팅.
            log.info("user : {} ", user.toString());

            //Jpa 인설트
            User userEntity = authService.회원가입(user);
            System.out.println(userEntity);
            return "auth/signin";
        }
    }
}
