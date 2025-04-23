package com.ChanDoTeam.ChanDoApp.models;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String category;
    private String color;

    @Column(name = "streak")
    private Integer streak = 0; // Стрик

    @Column(name = "stars")
    private Integer stars = 0; // Звезды для этой привычки

    @Column(name = "last_completed_date_time")
    private LocalDateTime lastCompletedDateTime; // Время последнего выполнения

    @Column(name = "start_date")
    private LocalTime startDate; // Дата начала привычки

    
    @Column(name = "VisibleDate")
    private LocalDate visibleDate;


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
        return streak != null ? streak : 0;
    }

    public void setStreak(Integer streak) {
        this.streak = streak;
    }

    public Integer getStars() {
        return stars != null ? stars : 0;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public LocalDateTime getLastCompletedDateTime() {
        return lastCompletedDateTime;
    }

    public void setLastCompletedDateTime(LocalDateTime lastCompletedDateTime) {
        this.lastCompletedDateTime = lastCompletedDateTime;
    }

    public LocalTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalTime startDate) {
        this.startDate = startDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getVisibleDate() {
        return visibleDate;
    }

    public void setVisibleDate(LocalDate visibleDate) {
        this.visibleDate = visibleDate;
    }
}