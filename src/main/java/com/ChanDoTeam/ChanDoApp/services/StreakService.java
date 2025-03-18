package com.ChanDoTeam.ChanDoApp.services;

import com.ChanDoTeam.ChanDoApp.models.Habit;
import com.ChanDoTeam.ChanDoApp.models.User;
import com.ChanDoTeam.ChanDoApp.repositories.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StreakService {

    @Autowired
    private HabitRepository habitRepository;

    @Value("${app.streak.update.interval}")
    private int streakUpdateInterval; // Время в секундах (например, 86400 для 1 дня)

    public void updateStreakForUser(User user) {
        List<Habit> habits = habitRepository.findByUser(user);
        LocalDateTime now = LocalDateTime.now();

        for (Habit habit : habits) {
            LocalDateTime lastCompletedDateTime = habit.getLastCompletedDateTime();

            if (lastCompletedDateTime == null) {
                // Если это первый заход, устанавливаем начальное значение стрика
                habit.setStreak(1);
                habit.setLastCompletedDateTime(now);
            } else {
                // Вычисляем разницу в секундах
                long secondsSinceLastVisit = java.time.Duration.between(lastCompletedDateTime, now).getSeconds();

                if (secondsSinceLastVisit >= streakUpdateInterval * 2) {
                    // Если прошло больше или равно удвоенному интервалу, сбрасываем стрик до 1
                    habit.setStreak(1);
                } else if (secondsSinceLastVisit >= streakUpdateInterval) {
                    // Если прошло больше или равно заданному интервалу, увеличиваем стрик
                    habit.setStreak(habit.getStreak() + 1);
                } else {
                    // Если интервал еще не истек, ничего не делаем
                    continue;
                }

                // Обновляем время последнего выполнения
                habit.setLastCompletedDateTime(now);
            }

            habitRepository.save(habit);
        }
    }
}