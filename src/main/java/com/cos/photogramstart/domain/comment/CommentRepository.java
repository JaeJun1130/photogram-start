package com.cos.photogramstart.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    //Comment 타입으로 리턴못받음 네이티브 쿼리(int)
//    @Modifying
//    @Query(value = "INSERT INTO comment(content,imageId,userId) values(:content,:imageId,:userId,now())",nativeQuery = true)
//    Comment mSave(String content, int imageId, int userId);
}
