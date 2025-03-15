package com.ChanDoTeam.ChanDoApp.models;

import jakarta.persistence.*;
<<<<<<< HEAD
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
@Table(name = "habit")
=======
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
>>>>>>> b80cf8db0ed71f2ad1f64892d866a61570165012
public class Habit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
<<<<<<< HEAD

    private String category;

    private String color;

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Добавлено для корректной обработки даты
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
=======
    private String category;
    private LocalDate startDate;
    private String color;

    // Связь с пользователем (ManyToOne)
    @ManyToOne(cascade = CascadeType.ALL) // Или CascadeType.REMOVE
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Конструктор по умолчанию
    public Habit() {}

    // Конструктор с параметрами
    public Habit(String title, String category, LocalDate startDate, String color, User user) {
        this.title = title;
        this.category = category;
        this.startDate = startDate;
        this.color = color;
        this.user = user;
    }
>>>>>>> b80cf8db0ed71f2ad1f64892d866a61570165012
}