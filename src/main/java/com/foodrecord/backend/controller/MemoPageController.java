package com.foodrecord.backend.controller;

import com.foodrecord.backend.entity.MemoPage;
import com.foodrecord.backend.repository.MemoPageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pages")
@RequiredArgsConstructor
public class MemoPageController {

    private final MemoPageRepository memoPageRepository;

    // 🔸 저장 (React의 POST 요청 처리)
    @PostMapping
    public ResponseEntity<?> addPage(@RequestBody MemoPage request) {
        if (memoPageRepository.existsByUserIdAndTitle(request.getUserId(), request.getTitle())) {
            return ResponseEntity.badRequest().body("이미 존재하는 페이지 제목입니다.");
        }

        MemoPage saved = memoPageRepository.save(
                MemoPage.builder()
                        .userId(request.getUserId())
                        .title(request.getTitle())
                        .content("")
                        .build()
        );

        return ResponseEntity.ok(saved);
    }

    // 🔹 해당 사용자 페이지 목록 불러오기
    @GetMapping
    public ResponseEntity<List<MemoPage>> getPages(@RequestParam String userId) {
        return ResponseEntity.ok(memoPageRepository.findByUserId(userId));
    }
}
