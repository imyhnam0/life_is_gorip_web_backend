package com.foodrecord.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.foodrecord.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    User findByEmail(String email); // ✅ 로그인용
}
