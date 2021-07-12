package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity //해당 config 파일로 시큐리티를 활성
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http); 삭제시 기존 시큐리티가 가지고있는 설정이 초기화됨
        http.csrf().disable();              //CSRF 비활성화 (default 값 true)
        http.authorizeRequests()
            .antMatchers("/", "/user/**", "/image/**", "/subscribe/**","/comment/**").authenticated() //해당 url은 인증이 필요
            .anyRequest().permitAll()   //그게 아닌 요청은 허용하겠다.
            .and()                      //그리고
            .formLogin()                //로그인 페이지
            .loginPage("/auth/signin")  //해당 url
            .defaultSuccessUrl("/");    //성공하면 / url로
    }
}
