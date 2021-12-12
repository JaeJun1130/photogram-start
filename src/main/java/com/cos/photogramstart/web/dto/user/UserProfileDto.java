package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserProfileDto {
    private boolean pageOwnerStatus;    //해당 프로필 주인 확인
    private int imageCount;             //이미지 갯수

    private boolean subScribeStatus;    //해당 구독상태
    private int subScribeCount;         //구독자 수

    private User user;
}
