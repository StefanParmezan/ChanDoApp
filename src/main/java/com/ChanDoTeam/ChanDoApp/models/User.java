package com.ChanDoTeam.ChanDoApp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String RegistrationDate;
    private int level;
    private String avatar = "ProfileSt";


    @Column(unique = true) // Уникальный email
    private String telegramId;

    private boolean notifiedToday = false; // Статус уведомления за сегодня

    private int age; // Возраст пользователя

    @Column(name = "total_stars")
    private Integer totalStars = 0; // Общее количество звезд пользователя

    @Column(name = "is_premium")
    private boolean isPremium = false; // Флаг премиум-статуса

    private int totalachivements = 0;

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

    public String getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(String telegramId) {
        this.telegramId = telegramId;
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

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public List<Habit> getHabits() {
        return habits;
    }

    public void setHabits(List<Habit> habits) {
        this.habits = habits;
    }

    public void setRegistrationDate(String registrationDate) {
        RegistrationDate = registrationDate;
    }

    public String getRegistrationDate() {
        return RegistrationDate;
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public int getTotalachivements() {
        return totalachivements;
    }

    public void setTotalachivements(int totalachivements) {
        this.totalachivements = totalachivements;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    // Метод для обновления totalStars
    public void updateTotalStars() {
        this.totalStars = habits.stream()
                .mapToInt(Habit::getStars)
                .sum();
    }
}