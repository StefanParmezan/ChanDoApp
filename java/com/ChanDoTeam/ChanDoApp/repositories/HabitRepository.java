package com.ChanDoTeam.ChanDoApp.repositories;

import com.ChanDoTeam.ChanDoApp.models.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {
    List<Habit> findByUserId(Long userId); // Получить привычки по ID пользователя
}