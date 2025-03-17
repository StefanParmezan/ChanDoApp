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
    private int streakUpdateInterval; // Время в секундах

    public void updateStreakForUser(User user) {
        List<Habit> habits = habitRepository.findByUser(user);
        LocalDateTime now = LocalDateTime.now();

        for (Habit habit : habits) {
            LocalDateTime lastVisitDateTime = habit.getLastVisitDateTime();

            if (lastVisitDateTime == null) {
                // Если это первый заход
                habit.setStreak(1);
            } else {
                // Вычисляем разницу в секундах
                long secondsSinceLastVisit = java.time.Duration.between(lastVisitDateTime, now).getSeconds();

                if (secondsSinceLastVisit >= streakUpdateInterval * 2) {
                    // Если прошло больше или равно удвоенному интервалу, сбрасываем стрик до 1
                    System.out.println("Habit setted to 1");
                    habit.setStreak(1);
                } else if (secondsSinceLastVisit >= streakUpdateInterval) {
                    // Если прошло больше или равно заданному интервалу, увеличиваем стрик
                    habit.setStreak(habit.getStreak() + 1);
                }
            }

            // Обновляем время последнего посещения
            habit.setLastVisitDateTime(now);

            habitRepository.save(habit);
        }
    }
} 