package com.ChanDoTeam.ChanDoApp.repositories;

import com.ChanDoTeam.ChanDoApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // Оставляем только одну версию метода
    Optional<User> findByTelegramId(int telegramId); // Измените на Optional, если хотите использовать его
    List<User> findAll();
    void deleteAll(); // Метод для удаления всех пользователей
}