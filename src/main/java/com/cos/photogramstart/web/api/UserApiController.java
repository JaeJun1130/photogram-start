package com.cos.photogramstart.web.api;

import com.cos.photogramstart.config.auth.PrinciPalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMResDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

    @PostMapping("/api/user/{userId}")
    public CMResDto<?> update(@PathVariable int userId,
                              @Valid UserUpdateDto userUpdateDto,
                              BindingResult bindingResult, //꼭 vaild 밑에 적어줘야 동작함
                              @AuthenticationPrincipal PrinciPalDetails princiPalDetails){
        if(bindingResult.hasErrors()){
            Map<String,String> errorMap = new HashMap<>();

            for(FieldError fieldError : bindingResult.getFieldErrors()){
                errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
                System.out.println("fieldError.getDefaultMessage() = " + fieldError.getDefaultMessage());
            }
            throw new CustomValidationApiException("유효성 검사 실패",errorMap);
        }
        User userEntity = userService.userUpdate(userId, userUpdateDto.toEntity());
        princiPalDetails.setUser(userEntity); //session 정보 수정

        return new CMResDto<>(1,"회원수정완료",userEntity ); //JPA 연관관계 때문에 무한 참조가 일어남 @JsonIgnoreProperties 사용
    }
}
