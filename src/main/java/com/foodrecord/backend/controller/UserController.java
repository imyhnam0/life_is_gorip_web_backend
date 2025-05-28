package com.foodrecord.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.foodrecord.backend.repository.UserRepository;
import com.foodrecord.backend.entity.User;

@CrossOrigin(origins = "http://localhost:3000") // ✅ CORS 허용
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("이미 사용 중인 이메일입니다.");
        }

        userRepository.save(user);
        return ResponseEntity.ok("{\"message\": \"회원가입이 완료되었습니다.\"}");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginUser) {
        User user = userRepository.findByEmail(loginUser.getEmail());

        if (user != null && user.getPassword().equals(loginUser.getPassword())) {
            return ResponseEntity.ok().body(
                java.util.Map.of(
                    "message", "로그인 성공",
                    "data", java.util.Map.of("accessToken", "fake-jwt-token")
                )
            );
        } else {
            return ResponseEntity.status(401).body("이메일 또는 비밀번호가 올바르지 않습니다.");
        }
    }

}

