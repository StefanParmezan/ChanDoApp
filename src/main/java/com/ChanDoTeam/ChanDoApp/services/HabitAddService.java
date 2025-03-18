package com.ChanDoTeam.ChanDoApp.services;

import com.ChanDoTeam.ChanDoApp.models.Habit;
import com.ChanDoTeam.ChanDoApp.models.User;
import com.ChanDoTeam.ChanDoApp.repositories.HabitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HabitAddService {

    private static final Logger logger = LoggerFactory.getLogger(HabitAddService.class);

    @Autowired
    private HabitRepository habitRepository;

    public void addHabit(Habit habit, User user) {
        // Проверяем, существует ли привычка с таким названием у пользователя
        if (habitRepository.existsByTitleAndUserId(habit.getTitle(), user.getId())) {
            throw new IllegalArgumentException("Привычка уже существует");
        }

        // Устанавливаем начальный стрик
        habit.setStreak(1);

        // Привязываем привычку к пользователю
        habit.setUser(user);

        // Сохраняем привычку
        habitRepository.save(habit);

        logger.info("Привычка '{}' успешно добавлена для пользователя '{}'", habit.getTitle(), user.getUsername());
    }
}