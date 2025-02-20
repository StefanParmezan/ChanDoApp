package com.ChanDoTeam.ChanDoApp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
}