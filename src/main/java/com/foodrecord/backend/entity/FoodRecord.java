package com.foodrecord.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "food_record")
public class FoodRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String date; // 또는 LocalDate

    @Column(columnDefinition = "TEXT")
    private String foodText;

    private String createdAt;

    private String updatedAt;
}
