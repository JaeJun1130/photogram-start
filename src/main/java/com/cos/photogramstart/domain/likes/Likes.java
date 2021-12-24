package com.cos.photogramstart.domain.likes;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import lombok.*;

import javax.persistence.*;

//JPA
@Builder
@AllArgsConstructor //필드값을 모두 포함한 생성자를 자동 생성해준다.
@NoArgsConstructor  //기본생성자
@Getter
@Setter
@Entity
/**
 * 복합 유니크 키
 * 두개의 키값은 중복이 발생하면 안되므로 복합유니크키 설정을 해준다.
 */
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "like_uk",
                        columnNames = {"imageId", "userid"}
                )
        }
)

public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB 번호 증가를 따라간다.
    private int id;

    @JoinColumn(name = "imageId")
    @ManyToOne
    private Image image; //N:1

    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;
}
