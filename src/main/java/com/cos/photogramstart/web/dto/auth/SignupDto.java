package com.cos.photogramstart.web.dto.auth;

import lombok.Data;

@Data //GET,SET
public class SignupDto {
    private String username;
    private String password;
    private String email;
    private String name;

    @Override
    public String toString() {
        return "SignupDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
