package com.ChanDoTeam.ChanDoApp.services;

import com.ChanDoTeam.ChanDoApp.models.Habit;
import com.ChanDoTeam.ChanDoApp.models.User;
import com.ChanDoTeam.ChanDoApp.repositories.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class HabitAddService {

    @Autowired
    private HabitRepository habitRepository;

    /**
     * Добавляет новую привычку для пользователя.
     *
     * @param habit Новая привычка.
     * @param user  Пользователь, которому принадлежит привычка.
     * @return Объект HabitAddResponse с результатом операции.
     */
    public HabitAddResponse addHabit(Habit habit, User user) {
        try {
            // Валидация данных
            validateHabit(habit);

            // Проверяем, существует ли привычка с таким названием у пользователя
            if (habitRepository.existsByTitleAndUserId(habit.getTitle(), user.getId())) {
                return new HabitAddResponse(null, "Эта привычка уже существует");
            }

            // Устанавливаем значения по умолчанию
            habit.setStreak(1); // Инициализируем стрик
            habit.setUser(user); // Привязываем привычку к пользователю

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

    /**
     * Валидация данных привычки.
     *
     * @param habit Привычка для валидации.
     * @throws IllegalArgumentException Если данные невалидны.
     */
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