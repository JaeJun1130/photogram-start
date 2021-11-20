package com.cos.photogramstart.config.auth;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class PrinciPalDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private User user;

    public PrinciPalDetails(User user) {
        this.user = user;
    }

    //권한은 여러개 가지고 있을 수 있기때문에 콜렉션 타입을 사용
    //<? extends GrantedAuthority> GrantedAuthority 를 상속하고 있는 ? 타입
    //ArrayList 는 Collection
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collector = new ArrayList<>();
        collector.add(()->{return user.getRole();});
        return collector;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
