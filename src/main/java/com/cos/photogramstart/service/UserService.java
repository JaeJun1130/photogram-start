package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User userUpdate(int userId, User user) {
        //1.영속화
        //repository 를 통해 조회해온 엔티티는 영속성 컨택스트에서 관리 됩니다.
        //findById 타입이 옵셔널임
        //get null 일때 NoSuchElementException
        User userEntity = userRepository.findById(userId).get();

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
}
