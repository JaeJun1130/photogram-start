package com.cos.photogramstart.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

//JPA
@AllArgsConstructor //필드값을 모두 포함한 생성자를 자동 생성해준다.
@NoArgsConstructor  //기본생성자
@Data
@Entity             //JPA에서는 엔티티는 테이블에 대응하는 하나의 클래스
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB 번호 증가를 따라간다.
    private int id;

    private String username;
    private String password;

    private String name;
    private String webSite;
    private String bio;
    private String email;
    private String phone;
    private String gender;
    private String profileImageUrl;
    private String role; //권한

    private LocalDateTime createDate;

    @PrePersist //디비의 insert 되기전에 실행.
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
