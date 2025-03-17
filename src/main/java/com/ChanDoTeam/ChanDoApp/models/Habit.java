package com.ChanDoTeam.ChanDoApp.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "habit")
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String category;
    private String color;

    @Column(name = "streak")
    private Integer streak; // Используем Integer, чтобы поддерживать null

    @Column(name = "last_visit_date_time")
    private LocalDateTime lastVisitDateTime; // Изменено на LocalDateTime

    @Column(name = "start_date")
    private LocalDate startDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getStreak() {
        return streak != null ? streak : 0; // Возвращаем 0, если streak == null
    }

    public void setStreak(Integer streak) {
        this.streak = streak;
    }

    public LocalDateTime getLastVisitDateTime() {
        return lastVisitDateTime;
    }

    public void setLastVisitDateTime(LocalDateTime lastVisitDateTime) {
        this.lastVisitDateTime = lastVisitDateTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}