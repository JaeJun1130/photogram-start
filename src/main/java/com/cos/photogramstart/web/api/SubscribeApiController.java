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

    @PostMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<?> subscribe(@AuthenticationPrincipal PrinciPalDetails princiPalDetails, @PathVariable int toUserId){
        subscribeService.구독(princiPalDetails.getUser().getId(),toUserId);
        return new ResponseEntity<>(new CMResDto<>(1,"구독성공",null), HttpStatus.OK);
    }

    @PostMapping("/api/unSubscribe/{toUserId}")
    public ResponseEntity<?> unSubscribe(@AuthenticationPrincipal PrinciPalDetails princiPalDetails, @PathVariable int toUserId){
        subscribeService.구독취소(princiPalDetails.getUser().getId(),toUserId);
        return new ResponseEntity<>(new CMResDto<>(1,"구독취소성공",null), HttpStatus.OK);
    }
}
