package com.ChanDoTeam.ChanDoApp.controllers;

import com.ChanDoTeam.ChanDoApp.models.Habit;
import com.ChanDoTeam.ChanDoApp.models.User;
import com.ChanDoTeam.ChanDoApp.repositories.HabitRepository;
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
import java.time.format.DateTimeFormatter;

@Controller
public class HabitAddController {
    private final UserService userService;
    private final HabitRepository habitRepository;

    @Autowired
    public HabitAddController(UserService userService, HabitRepository habitRepository) {
        this.userService = userService;
        this.habitRepository = habitRepository;
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
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        User user = userService.getUserByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println("Day: " + day);
        System.out.println("Month: " + month);
        System.out.println("Year: " + year);
        // Объединяем день, месяц и год в LocalDate
        LocalDate startDate = LocalDate.of(year, month, day);
        habit.setStartDate(startDate);

        habit.setUser(user);
        habitRepository.save(habit);

        return "redirect:/habitlist";
    }
}