package com.ChanDoTeam.ChanDoApp.controllers;

import com.ChanDoTeam.ChanDoApp.models.Habit;
import com.ChanDoTeam.ChanDoApp.models.User;
import com.ChanDoTeam.ChanDoApp.services.HabitAddService;
import com.ChanDoTeam.ChanDoApp.services.HabitAddResponse;
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
import java.time.LocalTime;

@Controller
public class HabitAddController {

    private final UserService userService;
    private final HabitAddService habitAddService;

    @Autowired
    public HabitAddController(
            UserService userService,
            HabitAddService habitAddService
    ) {
        this.userService = userService;
        this.habitAddService = habitAddService;
    }

    @GetMapping("/habitadd")
    public String showAddForm(Model model) {
        // Добавляем текущую дату в модель для формы
        LocalDate today = LocalDate.now();
        model.addAttribute("day", today.getDayOfMonth());
        model.addAttribute("month", today.getMonthValue());
        model.addAttribute("year", today.getYear());

        return "habitadd"; // Возвращаем форму добавления привычки
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
        // Получаем текущего пользователя
        User user = userService.getUserByUsername(userDetails.getUsername())
                .orElse(null);

        // Объединяем день, месяц и год в LocalDate
        habit.setNotificationTime(LocalTime.now());

        habit.setVisibleDate(LocalDate.now());

        // Устанавливаем начальное значение lastCompletedDateTime
        habit.setLastCompletedDateTime(LocalDateTime.now());

        // Пытаемся добавить привычку через сервис
        HabitAddService.HabitAddResponse response = habitAddService.addHabit(habit, user);

        // Если есть ошибка, добавляем сообщение в модель
        if (!response.isSuccess()) {
            model.addAttribute("error", response.getErrorMessage());
            return "HabitAdd";
        }

        // Если успех, перенаправляем на список привычек
        return "redirect:/habitlist";
    }
}