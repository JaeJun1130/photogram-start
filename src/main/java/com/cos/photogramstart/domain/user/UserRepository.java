package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

//어노테이션이 없어도 Jpa 상속시 ioc 자동등록
public interface UserRepository extends JpaRepository<User,Integer> {
}
