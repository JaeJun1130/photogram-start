package com.cos.photogramstart.domain.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Query(value = "SELECT * FROM Image WHERE userId in (SELECT toUserId FROM Subscribe WHERE fromUserId=:principalId)", nativeQuery = true)
    List<Image> mStory(int principalId);
}



