package com.ChanDoTeam.ChanDoApp.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @Column(unique = true) // Уникальный email
    private String email;

    private int age; // Возраст пользователя

    @Column(name = "total_stars")
    private Integer totalStars = 0; // Общее количество звезд пользователя

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Habit> habits;

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getTotalStars() {
        return totalStars != null ? totalStars : 0;
    }

    public void setTotalStars(Integer totalStars) {
        this.totalStars = totalStars;
    }

    public List<Habit> getHabits() {
        return habits;
    }

    public void setHabits(List<Habit> habits) {
        this.habits = habits;
    }

    // Метод для обновления totalStars
    public void updateTotalStars() {
        this.totalStars = habits.stream()
                .mapToInt(Habit::getStars)
                .sum();
    }
}