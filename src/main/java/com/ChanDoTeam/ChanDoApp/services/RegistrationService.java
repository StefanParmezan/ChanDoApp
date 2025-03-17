package com.ChanDoTeam.ChanDoApp.services;

import com.ChanDoTeam.ChanDoApp.models.User;
import com.ChanDoTeam.ChanDoApp.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(RegistrationService.class);
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserRepository userRepository,
                               BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Основные исправления:
    // 1. Заменил byte age на int age
    // 2. Убрал CsrfToken из импортов
    // 3. Исправил форматирование

    public RegistrationResponse registerUser(String username,
                                             String password,
                                             String confirmPassword,
                                             int age, // Changed from byte to int
                                             String email) {
        logger.debug("Проверка существования пользователя: {}", username);

        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            return new RegistrationResponse(null, null, "Пользователь уже существует");
        }

        Optional<User> existingEmail = userRepository.findByEmail(email);
        if (existingEmail.isPresent()) {
            return new RegistrationResponse(null, null, "Email уже занят");
        }

        String emailRegex = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if (!email.matches(emailRegex)) {
            return new RegistrationResponse(null, null, "Некорректный email");
        }

        if (age < 0 || age > 100) {
            return new RegistrationResponse(null, null, "Возраст должен быть 0-100");
        }

        if (!password.equals(confirmPassword)) {
            return new RegistrationResponse(null, null, "Пароли не совпадают");
        }

        if (username.isEmpty() || password.isEmpty()) {
            return new RegistrationResponse(null, null, "Заполните все поля");
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setAge(age); // Используем setAge вместо setDate
        newUser.setEmail(email);

        userRepository.save(newUser);

        return new RegistrationResponse(newUser, "Успешная регистрация", null);
    }

    private boolean isPasswordComplex(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Za-zА-Яа-я].*") &&
                password.matches(".*[!@#$%^&*].*") &&
                !isCommonPassword(password);
    }

    private boolean isCommonPassword(String password) {
        String[] common = {"12345678", "password", "qwertyui"};
        for (String p : common) {
            if (password.equalsIgnoreCase(p)) return true;
        }
        return false;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}