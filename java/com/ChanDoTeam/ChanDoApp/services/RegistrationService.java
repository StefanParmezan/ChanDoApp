package com.ChanDoTeam.ChanDoApp.services;

import com.ChanDoTeam.ChanDoApp.models.User;
import com.ChanDoTeam.ChanDoApp.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
<<<<<<< HEAD
=======
import org.springframework.security.web.csrf.CsrfToken;
>>>>>>> b80cf8db0ed71f2ad1f64892d866a61570165012
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
<<<<<<< HEAD
    public RegistrationService(UserRepository userRepository,
                               BCryptPasswordEncoder passwordEncoder) {
=======
    public RegistrationService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
>>>>>>> b80cf8db0ed71f2ad1f64892d866a61570165012
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

<<<<<<< HEAD
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
=======
    private boolean isPasswordComplex(String password) {
        if (password.length() < 8) {
            return false;
        }

        if (!password.matches(".*[a-zA-Z].*")){
            return false;
        }

        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {
            return false;
        }


        String[] simplePasswords = {
                "12345678", "123456789", "1234567890", "12345678910", "0987654321", "987654321", "87654321"
        };

        for (String simplePassword : simplePasswords) {
            if (password.equals(simplePassword)) {
                return false;
            }
        }

        return true;
    }

    public RegistrationResponse registerUser  (String username, String password, String confirmPassword, byte date, String email) {
        logger.debug("Проверка существования пользователя с именем: {}", username); // Логирование проверки имени пользователя

        // Проверка, существует ли пользователь с таким именем
        Optional<User> existingUser   = userRepository.findByUsername(username);
        if (existingUser .isPresent()) {
            logger.warn("Пользователь с именем {} уже существует в базе данных", username);
            return new RegistrationResponse(null, null, "Пользователь с таким именем уже существует");
        }

        // Проверка, существует ли пользователь с таким email
        Optional<User> existingEmailUser   = userRepository.findByEmail(email);
        if (existingEmailUser .isPresent()) {
            logger.warn("Пользователь с email {} уже существует в базе данных", email);
            return new RegistrationResponse(null, null, "Этот email уже занят");
        }

        // Проверка формата email
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!email.matches(emailRegex)) {
            logger.warn("Неправильно введена почта: {}", email);
            return new RegistrationResponse(null, null, "Неправильно введена почта");
        }

        // Проверка возраста
        if (date < 0 || date > 100) {
            logger.warn("Возраст должен быть от 0 до 100: {}", date);
            return new RegistrationResponse(null, null, "Возраст должен быть от 0 до 100!");
        }

        // Проверка совпадения паролей
        if (!password.equals(confirmPassword)) {
            logger.warn("Пароли не совпадают для пользователя: {}", username);
            return new RegistrationResponse(null, null, "Пароли не совпадают!");
        }

        // Проверка на пустые поля
        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            logger.warn("Все поля должны быть заполнены для пользователя: {}", username);
            return new RegistrationResponse(null, null, "Все поля должны быть заполнены!");
        }

        // Проверка сложности пароля
        if (!isPasswordComplex(password)) {
            logger.warn("Пароль не соответствует требованиям для пользователя: {}", username);
            return new RegistrationResponse(null, null, "Пароль должен быть более 8 символов, содержать хотя бы один символ (A-Z)(А-Я) и один спец.символ");
        }

        // Создание нового пользователя
        User newUser   = new User();
        newUser .setUsername(username);
        newUser .setPassword(passwordEncoder.encode(password)); // Хеширование пароля перед сохранением
        newUser .setDate(date);
        newUser .setEmail(email);

        // Сохранение пользователя в базе данных
        userRepository.save(newUser );
        // Вывод всей базы данных после регистрации пользователя
        List<User> allUsers = getAllUsers();
        logger.info("Список всех пользователей в базе данных:");
        for (User  user : allUsers) {
            logger.info("Пользователь: Имя: {}, Email: {}, Возраст: {}", user.getUsername(), user.getEmail(), user.getDate());
        }

        return new RegistrationResponse(newUser , "Регистрация прошла успешно", null);
    }

    // Метод для получения списка всех пользователей
    public List<User> getAllUsers() {
        logger.debug("Запрос списка всех пользователей"); // Логирование запроса списка пользователей
>>>>>>> b80cf8db0ed71f2ad1f64892d866a61570165012
        return userRepository.findAll();
    }
}