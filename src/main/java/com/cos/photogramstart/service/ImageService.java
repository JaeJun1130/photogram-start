package com.cos.photogramstart.service;

import com.cos.photogramstart.config.auth.PrinciPalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final ImageRepository imageRepository;

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional
    public void imgUpload(ImageUploadDto imageUploadDto, PrinciPalDetails PrinciPalDetails) {
        UUID uuid = UUID.randomUUID();  //유일성보장이지만 진짜 매우 작은 확률로 중복될수가 있는지만 거의 유일함
        String imageFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename();

        Path imageFilePath = Paths.get(uploadFolder + imageFileName);

        // 통신, I/O -> 예외가 발생할 수 있다.
        try {
            Files.write(imageFilePath, imageUploadDto.getFile().getBytes()); //이미지 경로, 이미지바이트
            //imageUploadDto.getFile().transferTo(imageFilePath); //뭔차이
        } catch (Exception e) {
            e.printStackTrace();
        }
        imageRepository.save(imageUploadDto.imageEntity(imageFileName, PrinciPalDetails.getUser()));
    }

    @Transactional(readOnly = true)
    public Page<Image> imageStory(int principalId, Pageable pageable) {
        Page<Image> images = imageRepository.mStory(principalId, pageable);

        //images에 좋아요 상태 담기
        images.forEach(image -> {
            image.getLikes().forEach(like -> {
                if (like.getUser().getId() == principalId) {  //해당 이미지에 좋아요한 사람들을 찾아서 현재 로그인한 사람이 좋아요 한것인지 비교
                    image.setLikeState(true);
                }
            });
        });
        return images;
    }

}
