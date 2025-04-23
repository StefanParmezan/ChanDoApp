package com.ChanDoTeam.ChanDoApp.services;

import com.ChanDoTeam.ChanDoApp.models.Habit;
import com.ChanDoTeam.ChanDoApp.models.User;
import com.ChanDoTeam.ChanDoApp.repositories.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class HabitAddService {

    @Autowired
    private HabitRepository habitRepository;

    public HabitAddResponse addHabit(Habit habit, User user) {
        try {
            // Валидация данных
            validateHabit(habit);

            // Проверяем, существует ли привычка с таким названием у пользователя
            if (habitRepository.existsByTitleAndUserId(habit.getTitle(), user.getId())) {
                return new HabitAddResponse(null, "Эта привычка уже существует");
            }

            // Проверяем лимит привычек для бесплатного аккаунта
            List<Habit> userHabits = habitRepository.findByUserId(user.getId());
            if (userHabits.size() >= 5 && !user.isPremium()) {
                return new HabitAddResponse(null, "Достигнут лимит привычек для бесплатного аккаунта. Приобретите премиум для добавления дополнительных привычек.");
            }

            // Устанавливаем значения по умолчанию
            habit.setStreak(1); // Инициализируем стрик
            habit.setUser(user); // Привязываем привычку к пользователю
            habit.setVisibleDate(LocalDate.now());

            // Сохраняем привычку в базе данных
            habitRepository.save(habit);

            return new HabitAddResponse("Привычка успешно добавлена!", null);
        } catch (IllegalArgumentException e) {
            // Возвращаем ошибку валидации
            return new HabitAddResponse(null, e.getMessage());
        } catch (Exception e) {
            // Возвращаем общую ошибку
            return new HabitAddResponse(null, "Произошла ошибка при добавлении привычки: " + e.getMessage());
        }
    }

    private void validateHabit(Habit habit) {
        if (habit == null) {
            throw new IllegalArgumentException("Привычка не может быть null");
        }
        if (habit.getTitle() == null || habit.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Название привычки обязательно для заполнения");
        }
        if (habit.getCategory() == null || habit.getCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("Категория привычки обязательна для заполнения");
        }
    }
}