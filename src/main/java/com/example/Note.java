package com.example;

import java.time.LocalDateTime;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "notes")
public class Note extends PanacheEntity {

    @Column(nullable = false)
    public String title;

    @Column(columnDefinition = "TEXT")
    public String content;

    @Column(name = "created_at", nullable = false, updatable = false)
    public LocalDateTime createdAt;
}
