package com.cos.photogramstart.web.api;

import com.cos.photogramstart.web.dto.user.UserUpdateDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {
    @PostMapping("/api/user/{id}")
    public String update(UserUpdateDto userUpdateDto){
        System.out.println("userUpdateDto = " + userUpdateDto);
        return "ok";
    }
}
