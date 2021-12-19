package com.cos.photogramstart.web.api;

import com.cos.photogramstart.config.auth.PrinciPalDetails;
import com.cos.photogramstart.service.SubscribeService;
import com.cos.photogramstart.web.dto.CMResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SubscribeApiController {
    private final SubscribeService subscribeService;

    /**
     * 해당 유저 구독하기
     *
     * @param princiPalDetails
     * @param toUserId
     * @return
     */
    @PostMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<?> subscribe(@AuthenticationPrincipal PrinciPalDetails princiPalDetails, @PathVariable int toUserId) {
        subscribeService.mSubscribe(princiPalDetails.getUser().getId(), toUserId);
        return new ResponseEntity<>(new CMResDto<>(1, "구독성공", null), HttpStatus.OK);
    }

    /**
     * 해당 유저 구독취소
     *
     * @param princiPalDetails
     * @param toUserId
     * @return
     */
    @PostMapping("/api/unSubscribe/{toUserId}")
    public ResponseEntity<?> unSubscribe(@AuthenticationPrincipal PrinciPalDetails princiPalDetails, @PathVariable int toUserId) {
        subscribeService.mUnSubscribe(princiPalDetails.getUser().getId(), toUserId);
        return new ResponseEntity<>(new CMResDto<>(1, "구독취소성공", null), HttpStatus.OK);
    }
}
