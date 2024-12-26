package com.example.project.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consultationId;

    private String question;

    @Enumerated(EnumType.STRING)
    private ConsultationStatus status; // 답변 상태 (답변완료, 미답변)

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and Setters

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.status = ConsultationStatus.UNANSWERED; // 기본 상태는 미답변
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
