package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe,Integer> {

    //Object 객체가 아닌 Int 값을 인서트하기위해 nativeQuery 만들어보자
    @Modifying //SELECT 가 아닌 수정을 하기위해선 해당 어노테이셔 필요
    @Query(value = "INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId, :toUserId, now()) ",nativeQuery = true)
    void mSubscribe(int fromUserId, int toUserId) throws Exception;

    @Modifying
    @Query(value = "DELETE FROM subscribe WHERE fromUserId = :fromUserId AND toUserId = :toUserId",nativeQuery = true)
    void mUnSubscribe(int fromUserId, int toUserId)throws Exception;
}
