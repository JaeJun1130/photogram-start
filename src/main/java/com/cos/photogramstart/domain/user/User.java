package com.cos.photogramstart.domain.user;

import com.cos.photogramstart.domain.image.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//JPA
@Builder
@AllArgsConstructor //필드값을 모두 포함한 생성자를 자동 생성해준다.
@NoArgsConstructor  //기본생성자
@Data
@Entity             //JPA에서는 엔티티는 테이블에 대응하는 하나의 클래스
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB 번호 증가를 따라간다.
    private int id;

    @Column(length = 20, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private String webSite;
    private String bio;

    @Column(nullable = false)
    private String email;

    private String phone;
    private String gender;
    private String profileImageUrl;
    private String role; //권한

    private LocalDateTime createDate;

    //mappedBy 연관관계의 주인이 아님, 그러므로 테이블에 컬럼을 생성하지않음
    //User Select 할때 UserId로 등록되어있는 image를 가져옴
    //fetch default LAZY -> SELECT 할떄는 안가져옴 그대신 getImages를 하면 가져옴
    //fetch EAGER -> select 할 때 전부가져옴

    //외래 키가 있는 곳을 주인으로 정해라. @JoinColumn이 있는 쪽.
    //주인이 아닌쪽은 읽기만 가능
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Image> images;

    @PrePersist //디비의 insert 되기전에 실행.
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
