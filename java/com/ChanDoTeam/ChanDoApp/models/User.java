package com.ChanDoTeam.ChanDoApp.models;

import jakarta.persistence.*;
<<<<<<< HEAD
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Habit> habits;

    @Column(unique = true) // Уникальный email
    private String email;  // Добавленное поле

    // Геттеры и сеттеры
    private int age; // Заменили byte на int
    // Геттеры и сеттеры
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public List<Habit> getHabits() { return habits; }
    public void setHabits(List<Habit> habits) { this.habits = habits; }
=======
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "UserData") // Укажите имя таблицы в базе данных
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматическая генерация ID
    private Long id; // ID пользователя

    private String username; // Имя пользователя
    private String password; // Пароль
    private byte date; // Возраст или дата рождения
    private String email; // Email пользователя
    private String habit; // Привычка
    private String habit_time;
    private String habit_type;

    // Связь с привычками (OneToMany)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Habit> habits;

    // Конструктор по умолчанию
    public User() {}

    // Конструктор с параметрами
    public User(Long id, String username, String password, byte date, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.date = date;
        this.email = email;
    }
>>>>>>> b80cf8db0ed71f2ad1f64892d866a61570165012
}