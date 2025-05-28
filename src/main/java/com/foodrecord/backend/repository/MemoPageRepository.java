package com.foodrecord.backend.repository;

import com.foodrecord.backend.entity.MemoPage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoPageRepository extends JpaRepository<MemoPage, Long> {
    List<MemoPage> findByUserId(String userId);
    boolean existsByUserIdAndTitle(String userId, String title);
}
