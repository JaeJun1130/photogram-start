package com.cos.photogramstart.domain.image;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Query(value = "SELECT * FROM Image WHERE userId in (SELECT toUserId FROM Subscribe WHERE fromUserId=:principalId) order by createDate", nativeQuery = true)
//    List<Image> mStory(int principalId, Pageable pageable);
    Page<Image> mStory(int principalId, Pageable pageable);

    @Query(value = "select I.* from Image I Inner Join Likes L on I.id = L.imageId group by I.id order by count(I.id) desc;", nativeQuery = true)
    List<Image> mPopular();

}



