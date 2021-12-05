package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscribeService {
    private final SubscribeRepository subscribeRepository;

    @Transactional
    public void 구독(int fromUserId, int toUserId){
        try {
            subscribeRepository.mSubscribe(fromUserId,toUserId);
        }catch (Exception e) {
            throw new CustomApiException("이미 구독하셨습니다.");
        }
    }

    @Transactional
    public void 구독취소(int fromUserId, int toUserId) {
        try {
            subscribeRepository.mUnSubscribe(fromUserId, toUserId);
        } catch (Exception e) {
            throw new CustomApiException("구독취소 실패");
        }
    }
}
