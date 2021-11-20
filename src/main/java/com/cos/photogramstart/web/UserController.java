package com.cos.photogramstart.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
    /**
     * 사용자 정
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public String profile(@PathVariable int id){

        return "/user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable int id){

        return "/user/update";
    }
}
