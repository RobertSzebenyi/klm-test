package com.robert.szebenyi.klmtest.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @CreationTimestamp
    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "last_updated_at", nullable = false)
    @LastModifiedDate
    Instant lastUpdatedAt;

    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now().truncatedTo(ChronoUnit.MILLIS);
        createdAt = now;
        lastUpdatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdatedAt = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    }
}
