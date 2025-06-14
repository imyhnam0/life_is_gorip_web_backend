package com.foodrecord.backend.service;

import com.foodrecord.backend.entity.FoodRecord;
import com.foodrecord.backend.repository.FoodRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodRecordService {

    @Autowired
    private FoodRecordRepository repository;

    public void saveOrUpdate(String userId, String date, String foodText) {
        repository.findByUserIdAndDate(userId, date)
            .ifPresentOrElse(record -> {
                record.setFoodText(foodText);
                repository.save(record);
            }, () -> {
                FoodRecord newRecord = FoodRecord.builder()
                    .userId(userId)
                    .date(date)
                    .foodText(foodText)
                    .build();
                repository.save(newRecord);
            });
    }
}
