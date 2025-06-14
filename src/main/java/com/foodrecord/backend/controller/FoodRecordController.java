package com.foodrecord.backend.controller;

import com.foodrecord.backend.entity.FoodRecord;
import com.foodrecord.backend.repository.FoodRecordRepository;
import com.foodrecord.backend.service.FoodRecordService;

import lombok.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FoodRecordController {

    @Autowired
    private FoodRecordService service;
    @Autowired
    private FoodRecordRepository repository;

    @PostMapping("/saveFoodLog")
    public String saveFood(@RequestBody FoodRequest request) {
        service.saveOrUpdate(request.userId, request.date, request.foodText);
        return "저장 완료";
    }
    // ⭐ 날짜별 식단 기록 조회용 API
    @GetMapping("/getFoodLog")
    public ResponseEntity<?> getFoodLog(@RequestParam String userId, @RequestParam String date) {
    return repository.findByUserIdAndDate(userId, date)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.noContent().build()); // 값이 없을 수도 있으니까
}

    @Getter
    @Setter
    public static class FoodRequest {
        public String userId;
        public String date;
        public String foodText;
    }
}
