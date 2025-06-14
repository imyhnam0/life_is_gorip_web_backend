package com.foodrecord.backend.controller;

import com.foodrecord.backend.entity.MemoPage;
import com.foodrecord.backend.repository.MemoPageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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
    // ✅ 🔸 제목과 userId 기준으로 내용 업데이트
    @PutMapping("/update")
    public ResponseEntity<String> updatePage(@RequestBody MemoPage updatedPage) {
        Optional<MemoPage> pageOpt = memoPageRepository.findByUserIdAndTitle(
                updatedPage.getUserId(), updatedPage.getTitle());

        if (pageOpt.isPresent()) {
            MemoPage page = pageOpt.get();
            page.setContent(updatedPage.getContent());
            memoPageRepository.save(page);
            return ResponseEntity.ok("내용 저장 완료");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("페이지를 찾을 수 없음");
        }
    }
    @PutMapping("/trash")
    public ResponseEntity<String> moveToTrash(@RequestBody MemoPage page) {
        Optional<MemoPage> target = memoPageRepository.findByUserIdAndTitle(page.getUserId(), page.getTitle());
        if (target.isPresent()) {
            MemoPage found = target.get();
            found.setDeleted(true);
            memoPageRepository.save(found);
            return ResponseEntity.ok("휴지통으로 이동됨");
        }
        return ResponseEntity.status(404).body("페이지 없음");
    }
    @DeleteMapping
    public ResponseEntity<String> deletePage(@RequestParam String userId, @RequestParam String title) {
        Optional<MemoPage> target = memoPageRepository.findByUserIdAndTitle(userId, title);
        if (target.isPresent()) {
            memoPageRepository.delete(target.get());
            return ResponseEntity.ok("완전히 삭제됨");
        }
        return ResponseEntity.status(404).body("페이지 없음");
    }
    @GetMapping("/trash")
    public ResponseEntity<List<MemoPage>> getTrashedPages(@RequestParam String userId) {
        return ResponseEntity.ok(memoPageRepository.findByUserIdAndDeletedTrue(userId));
    }



}
