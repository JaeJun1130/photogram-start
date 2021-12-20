package com.cos.photogramstart.web.api;

import com.cos.photogramstart.config.auth.PrinciPalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.service.ImageService;
import com.cos.photogramstart.web.dto.CMResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ImageApiController {

    private final ImageService imageService;

    @PostMapping("/api/story")
    public ResponseEntity<?> imageStory(@AuthenticationPrincipal PrinciPalDetails princiPalDetails) {
        List<Image> images = imageService.imageStory(princiPalDetails.getUser().getId());
        return new ResponseEntity<>(new CMResDto<>(1, "성공", images), HttpStatus.OK);
    }
}
