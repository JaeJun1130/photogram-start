package com.cos.photogramstart.domain.subscribe;

import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

//JPA
@Builder
@AllArgsConstructor //필드값을 모두 포함한 생성자를 자동 생성해준다.
@NoArgsConstructor  //기본생성자
@Data
@Entity

/**
 * 복합 유니크 키
 * 두개의 키값은 중복이 발생하면 안되므로 복합유니크키 설정을 해준다.
 */
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name="subscribe_uk",
                        columnNames = {"fromUserId","toUserId"}
                )
        }
)

/**
 * 유저와 유저가 구독하는 중간테이블
 * N:N 관게에서는 중간에 sub 테이블이 필수로 존재해야함
 * 1 : N : 1
 * Many
 */
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB 번호 증가를 따라간다.
    private int id;

    @JoinColumn(name = "fromUserId")    //컬럼이름지정
    @ManyToOne
    private User fromUser;

    @JoinColumn(name = "toUserId")
    @ManyToOne
    private User toUser;

    private LocalDateTime createDate;

    @PrePersist //디비의 insert 되기전에 실행.
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

    public boolean haveSubscriber() {
        return toUser != null;
    }
}
