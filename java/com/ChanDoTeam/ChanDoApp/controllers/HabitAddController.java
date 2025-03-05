package com.ChanDoTeam.ChanDoApp.controllers;

import com.ChanDoTeam.ChanDoApp.models.Habit;
import com.ChanDoTeam.ChanDoApp.repositories.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HabitAddController {

    @Autowired
    private HabitRepository habitRepository;

    // Отображение страницы добавления привычки
    @GetMapping("/habitadd")
    public String habitAddPage() {
        return "HabitAdd"; // Имя HTML-файла (без расширения)
    }

    // Обработка добавления привычки
    @PostMapping("/api/habits")
    @ResponseBody
    public Habit addHabit(@RequestBody Habit habit) {
        // Здесь можно добавить логику для извлечения userId из токена
        habit.setUserId(1L); // Заглушка: userId = 1
        return habitRepository.save(habit);
    }
}