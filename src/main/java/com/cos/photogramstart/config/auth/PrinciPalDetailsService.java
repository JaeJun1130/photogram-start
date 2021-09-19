package com.cos.photogramstart.config.auth;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrinciPalDetailsService implements UserDetailsService { //loginProcessingUrl

    private final UserRepository userRepository;

    @Autowired
    public PrinciPalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 1.password는 알아서 시큐리티가 비교해줌
     * 2.리턴성공시 데이터를 자동으로 세션에 넣어준다
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = userRepository.findByUsername(username);
        if(byUsername==null){
            return null;
        }else{
            return new PrinciPalDetails(byUsername);
        }
    }
}
