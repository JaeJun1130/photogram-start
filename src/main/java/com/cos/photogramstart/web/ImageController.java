package com.cos.photogramstart.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageController {

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
}
