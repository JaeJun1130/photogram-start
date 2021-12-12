package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrinciPalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    /**
     * 사용자 프로필지 페이지
     * @param id
     * @return
     */
    @GetMapping("/user/{pageUserId}")
    public String profile(@PathVariable int pageUserId, Model model,@AuthenticationPrincipal PrinciPalDetails princiPalDetails){
        UserProfileDto userProfileDto = userService.userProfile(pageUserId,princiPalDetails.getUser().getId());

        model.addAttribute("userProfileDto",userProfileDto);
        return "/user/profile";
    }

    /**
     * 사용자 프로필 상세 페이
     * @param id
     * @param princiPalDetails
     * @param model
     * @return
     */
    @GetMapping("/user/{id}/update")
    public String update(@PathVariable int id, @AuthenticationPrincipal PrinciPalDetails princiPalDetails, Model model){
        // 1. 방식으로 말고 어노테이션을 사용해 session을 가져올 수 있음
//        PrinciPalDetails userDetail = (PrinciPalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = userDetail.getUser();
//        String username = userDetail.getUsername();
//        System.out.println("##user.toString## = " + user.toString());
//        System.out.println("##username## = " + username);
        
        // 2. @AuthenticationPrincipal
        String username = princiPalDetails.getUsername();

        model.addAttribute("principal", princiPalDetails.getUser());

        return "/user/update";
    }
}
