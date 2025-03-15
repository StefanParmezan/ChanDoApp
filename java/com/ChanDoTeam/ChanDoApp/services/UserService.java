package com.ChanDoTeam.ChanDoApp.services;

<<<<<<< HEAD
import com.ChanDoTeam.ChanDoApp.models.Habit;
import com.ChanDoTeam.ChanDoApp.models.User;
import com.ChanDoTeam.ChanDoApp.repositories.HabitRepository;
=======
import com.ChanDoTeam.ChanDoApp.models.User;
>>>>>>> b80cf8db0ed71f2ad1f64892d866a61570165012
import com.ChanDoTeam.ChanDoApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
=======

import java.util.List;
>>>>>>> b80cf8db0ed71f2ad1f64892d866a61570165012
import java.util.Optional;

@Service
public class UserService {
<<<<<<< HEAD
    private final UserRepository userRepository;
    private final HabitRepository habitRepository;

    @Autowired
    public UserService(UserRepository userRepository, HabitRepository habitRepository) {
        this.userRepository = userRepository;
        this.habitRepository = habitRepository;
    }

    public void saveHabit(Habit habit) {
        habitRepository.save(habit);
    }

=======

    @Autowired
    private UserRepository userRepository;

    // Получить userId по username
>>>>>>> b80cf8db0ed71f2ad1f64892d866a61570165012
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

<<<<<<< HEAD
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
=======
    // Получить пользователя по username
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Получить пользователя по email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Получить всех пользователей
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Удалить всех пользователей
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
>>>>>>> b80cf8db0ed71f2ad1f64892d866a61570165012
}