package com.cos.photogramstart.web.api;

import com.cos.photogramstart.config.auth.PrinciPalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.service.ImageService;
import com.cos.photogramstart.service.LikesService;
import com.cos.photogramstart.web.dto.CMResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ImageApiController {

    private final ImageService imageService;
    private final LikesService likesService;

    /**
     * 게시물 조회
     *
     * @param princiPalDetails
     * @param pageable
     * @return
     */
    @PostMapping("/api/story")
    public ResponseEntity<?> imageStory(@AuthenticationPrincipal PrinciPalDetails princiPalDetails,
                                        @PageableDefault(size = 3) Pageable pageable) {
        Page<Image> images = imageService.imageStory(princiPalDetails.getUser().getId(), pageable);


        return new ResponseEntity<>(new CMResDto<>(1, "성공", images), HttpStatus.OK);
    }

    /**
     * 게시물 좋아요
     *
     * @param imageId
     * @param princiPalDetails
     * @return
     */
    @PostMapping("/api/image/{imageId}/likes")
    public ResponseEntity<?> likes(@PathVariable int imageId, @AuthenticationPrincipal PrinciPalDetails princiPalDetails) {
        likesService.likes(imageId, princiPalDetails.getUser().getId());
        return new ResponseEntity<>(new CMResDto<>(1, "좋아요 성공", null), HttpStatus.CREATED);
    }

    /**
     * 게시물 좋아요 취소
     *
     * @param imageId
     * @param princiPalDetails
     * @return
     */
    @DeleteMapping("/api/image/{imageId}/likes")
    public ResponseEntity<?> unLikes(@PathVariable int imageId, @AuthenticationPrincipal PrinciPalDetails princiPalDetails) {
        likesService.unLikes(imageId, princiPalDetails.getUser().getId());
        return new ResponseEntity<>(new CMResDto<>(1, "좋아요 취소", null), HttpStatus.OK);
    }
}
