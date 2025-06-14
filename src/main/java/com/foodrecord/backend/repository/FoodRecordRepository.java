package com.foodrecord.backend.repository;

import com.foodrecord.backend.entity.FoodRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodRecordRepository extends JpaRepository<FoodRecord, Long> {
    Optional<FoodRecord> findByUserIdAndDate(String userId, String date);
}
