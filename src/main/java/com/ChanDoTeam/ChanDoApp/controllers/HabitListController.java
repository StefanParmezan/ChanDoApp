package com.ChanDoTeam.ChanDoApp.controllers;

import com.ChanDoTeam.ChanDoApp.models.Habit;
import com.ChanDoTeam.ChanDoApp.models.User;
import com.ChanDoTeam.ChanDoApp.repositories.HabitRepository;
import com.ChanDoTeam.ChanDoApp.services.StreakService;
import com.ChanDoTeam.ChanDoApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HabitListController {

    private final HabitRepository habitRepository;
    private final UserService userService;
    private final StreakService streakService;

    @Autowired
    public HabitListController(HabitRepository habitRepository, UserService userService, StreakService streakService) {
        this.habitRepository = habitRepository;
        this.userService = userService;
        this.streakService = streakService;
    }

    @GetMapping("/habitlist")
    public String showHabits(Model model, Authentication authentication) {
        User user = userService.getUserByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Обновляем стрик для привычек пользователя
        streakService.updateStreakForUser(user);

        // Получаем список привычек пользователя
        List<Habit> habits = habitRepository.findByUser(user);

        // Логирование

        System.out.println("Loaded habits: " + habits);
        habits.forEach(habit -> {
            System.out.println("User: " + habit.getUser());
            System.out.println("Habit Title: " + habit.getTitle());
            System.out.println("Category: " + habit.getCategory());
            System.out.println("Start Date: " + habit.getStartDate());
            System.out.println("Color: " + habit.getColor());
            System.out.println("Streak: " + habit.getStreak());
        });

        // Передаем данные в шаблон
        model.addAttribute("habits", habits);
        return "habitlist";
    }
}