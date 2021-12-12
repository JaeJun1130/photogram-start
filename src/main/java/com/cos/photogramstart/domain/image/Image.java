package com.cos.photogramstart.domain.image;

import com.cos.photogramstart.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

//JPA
@Builder
@AllArgsConstructor //필드값을 모두 포함한 생성자를 자동 생성해준다.
@NoArgsConstructor  //기본생성자
@Getter
@Setter
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB 번호 증가를 따라간다.
    private int id;

    private String caption;
    private String postImageUrl;    //사진을 전송받아서 그사진을 서버에 특정폴더에 저장 - 그 경로를 인서트함

    @JoinColumn(name="userId")
    @ManyToOne
    private User user; // 1 : N
    //이미지 좋아요

    //댓글

    private LocalDateTime createDate;

    @PrePersist //디비의 insert 되기전에 실행.
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
