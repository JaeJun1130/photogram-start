package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateDto {
    //@NotNull 은 위에 살펴본 것 처럼 이름 그대로 Null만 허용하지 않습니다.
    //@NotEmpty 는 null 과 "" 둘 다 허용하지 않게 합니다.
    //@NotBlank 는 null 과 "" 과 " " 모두 허용하지 않습니다.
    @NotBlank
    private String name;        //필수
    @NotBlank
    private String password;    //필수
    private String website;
    private String bio;
    private String phone;
    private String gender;
    private String email;

    //null 체크 필요
    public User toEntity(){
        return User.builder()
                .name(name)
                .password(password)
                .webSite(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .email(email)
                .build();
    }
}
