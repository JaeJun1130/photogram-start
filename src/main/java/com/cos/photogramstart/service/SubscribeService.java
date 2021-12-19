package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscribeService {
    private final SubscribeRepository subscribeRepository;
    private final EntityManager entityManager; //Repository는 EntityManager를 구현해서 만들어져있는 구현체.

    @Transactional
    public void mSubscribe(int fromUserId, int toUserId) {
        try {
            subscribeRepository.mSubscribe(fromUserId, toUserId);
        } catch (Exception e) {
            throw new CustomApiException("이미 구독하셨습니다.");
        }
    }

    @Transactional
    public void mUnSubscribe(int fromUserId, int toUserId) {
        try {
            subscribeRepository.mUnSubscribe(fromUserId, toUserId);
        } catch (Exception e) {
            throw new CustomApiException("구독취소 실패");
        }
    }

    // Subscribe의 타입으로 받을 수 없으므로 Entity를 이용한 Query 생성
    public List<SubscribeDto> userSubscriberList(int principalId, int pageUserId) {
        //Query 작성
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT U.id, U.username, U.profileImageUrl, ");
        sb.append("if((SELECT 1 from Subscribe WHERE fromUserId = ? and toUserId = U.id),1,0) as subscribeStatus, ");
        sb.append("if((? = S.id),1,0) as equalsUserStatus ");
        sb.append("FROM User U ");
        sb.append("INNER JOIN Subscribe S on S.toUserId = U.id ");
        sb.append("WHERE ");
        sb.append("S.fromUserId = ?");

        System.out.println("stringBuffer = " + sb.toString());

        //Query 완성
        Query query = entityManager.createNativeQuery(sb.toString())
                .setParameter(1, principalId)
                .setParameter(2, principalId)
                .setParameter(3, pageUserId);

        //Query 결과 Dto 맵핑(qlrm 라이브러리를 통해 Dto에 맵핑)
        JpaResultMapper jpaResultMapper = new JpaResultMapper();

        return jpaResultMapper.list(query, SubscribeDto.class);
    }
}
