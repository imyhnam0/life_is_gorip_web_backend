package com.foodrecord.backend.repository;

import java.util.Optional;
import com.foodrecord.backend.entity.MemoPage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoPageRepository extends JpaRepository<MemoPage, Long> {
    List<MemoPage> findByUserId(String userId);
    boolean existsByUserIdAndTitle(String userId, String title);
    Optional<MemoPage> findByUserIdAndTitle(String userId, String title);
    List<MemoPage> findByUserIdAndDeletedFalse(String userId);
    List<MemoPage> findByUserIdAndDeletedTrue(String userId);
   


}
