package com.ChanDoTeam.ChanDoApp.services;

import com.ChanDoTeam.ChanDoApp.models.Habit;
import com.ChanDoTeam.ChanDoApp.models.User;
import com.ChanDoTeam.ChanDoApp.repositories.HabitRepository;
import com.ChanDoTeam.ChanDoApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StreakService {

    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${app.streak.update.interval}")
    private int streakUpdateInterval; // Время в секундах (например, 86400 для 1 дня)

    public void updateStreakForUser(User user) {
        List<Habit> habits = habitRepository.findByUser(user);
        LocalDateTime now = LocalDateTime.now();

        for (Habit habit : habits) {
            LocalDateTime lastCompleted = habit.getLastCompletedDateTime();
            int currentStreak = habit.getStreak() != null ? habit.getStreak() : 0;

            if (lastCompleted == null) {
                // Первый день стрика
                habit.setStreak(1);
                habit.setStars(habit.getStars() + 1); // Начисляем 1 звезду за первый день
            } else {
                long secondsSinceLastVisit = java.time.Duration.between(lastCompleted, now).getSeconds();

                if (secondsSinceLastVisit >= streakUpdateInterval * 2) {
                    // Прерывание стрика: сбрасываем стрик до 1 и забираем 70% звезд
                    int starsToRemove = (int) (habit.getStars() * 0.7);
                    habit.setStars(Math.max(habit.getStars() - starsToRemove, 0));
                    habit.setStreak(1);
                } else if (secondsSinceLastVisit >= streakUpdateInterval) {
                    // Увеличиваем стрик
                    int newStreak = currentStreak + 1;
                    habit.setStreak(newStreak);

                    // Начисляем звезды
                    habit.setStars(habit.getStars() + 1); // +1 звезда за новый день

                    // Бонусные звезды за кратность 5
                    if (newStreak % 5 == 0) {
                        habit.setStars(habit.getStars() + 1); // +3 бонусные звезды
                    }
                } else {
                    // Интервал еще не истек
                    continue;
                }

                // Обновляем время последнего выполнения
                habit.setLastCompletedDateTime(now);
            }

            habitRepository.save(habit);
        }

        // Обновляем totalStars пользователя
        user.updateTotalStars();
        userRepository.save(user);
    }
}