package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;

@Data
public class UserUpdateDto {
    private String name;        //필수
    private String password;    //필수
    private String website;
    private String bio;
    private String phone;
    private String gender;

    //null 체크 필요
    public User toEntity(){
        return User.builder()
                .name(name)
                .password(password)
                .webSite(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }
}
