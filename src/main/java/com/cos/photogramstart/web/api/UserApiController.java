package com.cos.photogramstart.web.api;

import com.cos.photogramstart.config.auth.PrinciPalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.service.SubscribeService;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMResDto;
import com.cos.photogramstart.web.dto.auth.SignupDto;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;
    private final SubscribeService subscribeService;

    /**
     * 현재 유저의 구독리스트 GET
     *
     * @param paegUserId
     * @param princiPalDetails
     * @return
     */
    @PostMapping("/api/user/{paegUserId}/subscribe")
    public ResponseEntity<?> subscribeList(@PathVariable int paegUserId, @AuthenticationPrincipal PrinciPalDetails princiPalDetails) {
        List<SubscribeDto> subscribeDtoList = subscribeService.userSubscriberList(princiPalDetails.getUser().getId(), paegUserId);
        return new ResponseEntity<>(new CMResDto<>(1, "구독자 리스트 가져오기 성공", subscribeDtoList), HttpStatus.OK);
    }

    /**
     * 회정정보 업데이트
     *
     * @param userId
     * @param userUpdateDto
     * @param bindingResult
     * @param princiPalDetails
     * @return
     */
    @PostMapping("/api/user/{userId}")
    public CMResDto<?> update(@PathVariable int userId,
                              @Valid UserUpdateDto userUpdateDto,
                              BindingResult bindingResult, //꼭 vaild 밑에 적어줘야 동작함
                              @AuthenticationPrincipal PrinciPalDetails princiPalDetails) {

        User userEntity = userService.userUpdate(userId, userUpdateDto.toEntity());
        princiPalDetails.setUser(userEntity); //session 정보 수정

        return new CMResDto<>(1, "회원수정완료", userEntity); //JPA 연관관계 때문에 무한 참조가 일어남 @JsonIgnoreProperties 사용
    }

    @PutMapping("/api/user/{principalId}/profileImageUrl")
    public ResponseEntity<?> profileImageUrlUpdate(@PathVariable int principalId, MultipartFile profileImageFile,
                                                   @AuthenticationPrincipal PrinciPalDetails princiPalDetails) {
        User userEntity = userService.profileImageUrlUpdate(principalId, profileImageFile);
        princiPalDetails.setUser(userEntity);
        return new ResponseEntity<>(new CMResDto<>(1, "프로필사진변경", userEntity), HttpStatus.OK);
    }
}
