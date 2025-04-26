package com.ChanDoTeam.ChanDoApp.services;

import com.ChanDoTeam.ChanDoApp.models.Habit;
import com.ChanDoTeam.ChanDoApp.models.User;
import com.ChanDoTeam.ChanDoApp.repositories.HabitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class HabitAddService {
    private static final Logger logger = LoggerFactory.getLogger(HabitAddService.class);

    private final HabitRepository habitRepository;
    private final TelegramNotifier telegramNotifier;

    @Autowired
    public HabitAddService(HabitRepository habitRepository,
                           TelegramNotifier telegramNotifier) {
        this.habitRepository = habitRepository;
        this.telegramNotifier = telegramNotifier;
    }

    public HabitAddResponse addHabit(Habit habit, User user) {
        try {
            validateHabit(habit);

            if (habitRepository.existsByTitleAndUserId(habit.getTitle(), user.getId())) {
                return new HabitAddResponse(false, "Эта привычка уже существует");
            }

            List<Habit> userHabits = habitRepository.findByUserId(user.getId());
            if (userHabits.size() >= 5 && !user.isPremium()) {
                return new HabitAddResponse(false, "Достигнут лимит привычек для бесплатного аккаунта");
            }

            habit.setStreak(0);
            habit.setUser(user);
            habit.setVisibleDate(LocalDate.now());
            habit.setNotifiedToday(false);

            Habit savedHabit = habitRepository.save(habit);
            telegramNotifier.sendHabitAddedNotification(user.getTelegramId(), savedHabit);

            return new HabitAddResponse(true, "Привычка успешно добавлена!");
        } catch (IllegalArgumentException e) {
            return new HabitAddResponse(false, e.getMessage());
        } catch (Exception e) {
            logger.error("Error adding habit: {}", e.getMessage(), e);
            return new HabitAddResponse(false, "Произошла ошибка при добавлении привычки");
        }
    }

    private void validateHabit(Habit habit) {
        if (habit == null) {
            throw new IllegalArgumentException("Привычка не может быть null");
        }
        if (habit.getTitle() == null || habit.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Название привычки обязательно");
        }
        if (habit.getCategory() == null || habit.getCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("Категория привычки обязательна");
        }
    }

    public static class HabitAddResponse {
        private final boolean success;
        private final String errorMessage;

        public HabitAddResponse(boolean success, String errorMessage) {
            this.success = success;
            this.errorMessage = errorMessage;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public String getMessage() {
            return success ? "Привычка успешно добавлена!" : errorMessage;
        }
    }
}