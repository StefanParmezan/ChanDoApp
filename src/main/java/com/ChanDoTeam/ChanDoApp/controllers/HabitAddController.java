package com.ChanDoTeam.ChanDoApp.controllers;

import com.ChanDoTeam.ChanDoApp.models.Habit;
import com.ChanDoTeam.ChanDoApp.models.User;
import com.ChanDoTeam.ChanDoApp.repositories.HabitRepository;
import com.ChanDoTeam.ChanDoApp.services.HabitAddService;
import com.ChanDoTeam.ChanDoApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class HabitAddController {

    private final UserService userService;
    private final HabitRepository habitRepository;
    private final HabitAddService habitAddService;

    @Autowired
    public HabitAddController(
            UserService userService,
            HabitRepository habitRepository,
            HabitAddService habitAddService
    ) {
        this.userService = userService;
        this.habitRepository = habitRepository;
        this.habitAddService = habitAddService;
    }

    @GetMapping("/habitadd")
    public String showAddForm(Model model) {
        LocalDate today = LocalDate.now();
        model.addAttribute("day", today.getDayOfMonth());
        model.addAttribute("month", today.getMonthValue());
        model.addAttribute("year", today.getYear());

        return "habitadd";
    }

    @PostMapping("/habitadd")
    public String addHabit(
            @RequestParam int day,
            @RequestParam int month,
            @RequestParam int year,
            @ModelAttribute Habit habit,
            @AuthenticationPrincipal UserDetails userDetails,
            Model model
    ) {
        User user = userService.getUserByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Объединяем день, месяц и год в LocalDate
        LocalDate startDate = LocalDate.of(year, month, day);
        habit.setStartDate(startDate);

        // Устанавливаем начальное значение lastCompletedDateTime
        habit.setLastCompletedDateTime(LocalDateTime.now());

        try {
            // Используем сервис для добавления привычки
            habitAddService.addHabit(habit, user);
        } catch (IllegalArgumentException e) {
            // Если привычка уже существует, возвращаем ошибку на страницу
            model.addAttribute("error", e.getMessage());
            return "habitadd"; // Возвращаемся к форме добавления
        }

        return "redirect:/habitlist";
    }
}