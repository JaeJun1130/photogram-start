package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.likes.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final LikesRepository likesRepository;

    @Transactional
    public void likes(int imageId, int id) {
        likesRepository.mLikes(imageId, id);
    }

    @Transactional
    public void unLikes(int imageId, int id) {
        likesRepository.mUnLikes(imageId, id);
    }
}
