package com.foodrecord.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "memo_pages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemoPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;
}
