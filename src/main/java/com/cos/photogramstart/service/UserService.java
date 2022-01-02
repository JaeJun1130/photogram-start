package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${file.path}")
    private String uploadFolder;

    //영속성 컨텍스트가 변경이 됐는지 감지하고 있지 않음 (더티체크안함)
    //영속성 컨텍스트가 조금더 일을 하지않아도 됨
    @Transactional(readOnly = true)
    public UserProfileDto userProfile(int pageUserId, int userId) {
        UserProfileDto userProfileDto = new UserProfileDto();

        User userEntity = userRepository.findById(pageUserId).orElseThrow(() -> {
            throw new CustomException("해당 프로필은 없는 프로필 입니다.");
        });

        userProfileDto.setUser(userEntity);
        userProfileDto.setPageOwnerStatus(pageUserId == userId); //해당사용자면 1 아니면 -1
        userProfileDto.setImageCount(userEntity.getImages().size());

        int subscribeStatus = subscribeRepository.mSubscribeStatus(userId, pageUserId);
        int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);

        userProfileDto.setSubScribeStatus(subscribeStatus == 1);
        userProfileDto.setSubScribeCount(subscribeCount);

        userEntity.getImages().forEach(like -> {
            like.setLikeCount(like.getLikes().size());
        });

        return userProfileDto;
    }

    @Transactional
    public User userUpdate(int userId, User user) {
        //1.영속화
        //repository 를 통해 조회해온 엔티티는 영속성 컨택스트에서 관리 됩니다.
        //findById 타입이 옵셔널임
        //get null 일때 NoSuchElementException

        //Supplier
        User userEntity = userRepository.findById(userId).orElseThrow(() -> {
            throw new CustomValidationApiException("존재하지 않는 회원입니다");
        });

        
        //2.영속화된 객체를 수정 - 더티체크(업데이트 완료)
        // user 에 값이 변경되면 자동으로 인지해 변경 사항을 반영 (update) 합니다.
        // userRepository.save(user); save 함수를 호출할 필요가 없습니다.
        userEntity.setName(user.getName());
        userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setBio(user.getBio());
        userEntity.setEmail(user.getEmail());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());
        userEntity.setWebSite(user.getWebSite());

        return userEntity;
    }

    @Transactional
    public User profileImageUrlUpdate(int principalId, MultipartFile profileImageFile) {
        UUID uuid = UUID.randomUUID();  //유일성보장이지만 진짜 매우 작은 확률로 중복될수가 있는지만 거의 유일함
        String imageFileName = uuid + "_" + profileImageFile.getOriginalFilename();

        Path imageFilePath = Paths.get(uploadFolder + imageFileName);

        // 통신, I/O -> 예외가 발생할 수 있다.
        try {
            Files.write(imageFilePath, profileImageFile.getBytes()); //이미지 경로, 이미지바이트
            //imageUploadDto.getFile().transferTo(imageFilePath); //뭔차이
        } catch (Exception e) {
            e.printStackTrace();
        }
        User userEntity = userRepository.findById(principalId).orElseThrow(() -> {
            throw new CustomApiException("유저정보를 찾을 수 업습니다.");
        });
        userEntity.setProfileImageUrl(imageFileName);

        return userEntity;
    }
}
