package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrinciPalDetails;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.ImageService;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ImageController {
    private final ImageService imageService;

    /**
     * 로그인 성공후 메인 페이지
     * @return
     */
    @GetMapping({"/","/image/story"})
    public String story(){

        return "/image/story";
    }

    /**
     * 인기 게시물
     * @return
     */
    @GetMapping("/image/popular")
    public String popular(){

        return "/image/popular";
    }

    /**
     * 인기 게시물
     * @return
     */
    @GetMapping("/image/upload")
    public String upload(){

        return "/image/upload";
    }

    @PostMapping("/image")
    public String imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrinciPalDetails princiPalDetails){
        if(imageUploadDto.getFile().isEmpty()){
            throw new CustomValidationException("이미지를 첨부해주세요.",null);
        }
        //서비스 호출
        imageService.사진업로드(imageUploadDto,princiPalDetails);
        return "redirect:/user/"+princiPalDetails.getUser().getId();
    }
}
