package com.ChanDoTeam.ChanDoApp.controllers;

import com.ChanDoTeam.ChanDoApp.models.Habit;
import com.ChanDoTeam.ChanDoApp.repositories.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HabitListController {

    @Autowired
    private HabitRepository habitRepository;

    // Отображение страницы списка привычек
    @GetMapping("/habitlist")
    public String habitListPage() {
        return "HabitList"; // Имя HTML-файла (без расширения)
    }

    // Получение списка привычек для JavaScript
    @GetMapping("/api/habits")
    @ResponseBody
    public List<Habit> getHabits() {
        // Здесь можно добавить логику для извлечения userId из токена
        Long userId = 1L; // Заглушка: userId = 1
        return habitRepository.findByUserId(userId);
    }
}