package com.ChanDoTeam.ChanDoApp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Habit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
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
}