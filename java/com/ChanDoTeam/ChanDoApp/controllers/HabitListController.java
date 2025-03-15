package com.ChanDoTeam.ChanDoApp.controllers;

import com.ChanDoTeam.ChanDoApp.models.Habit;
import com.ChanDoTeam.ChanDoApp.models.User;
import com.ChanDoTeam.ChanDoApp.repositories.HabitRepository;
import com.ChanDoTeam.ChanDoApp.services.HabitListService;
import com.ChanDoTeam.ChanDoApp.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HabitListController {
    private final HabitRepository habitRepository;
    private final UserService userService;

    @Autowired
    public HabitListController(HabitRepository habitRepository, UserService userService) {
        this.habitRepository = habitRepository;
        this.userService = userService;
    }

    @GetMapping("/habitlist")
    public String showHabits(Model model, Authentication authentication) {
        User user = userService.getUserByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Habit> habits = habitRepository.findByUser(user);
        System.out.println("Loaded habits: " + habits); // Логирование
        habits.forEach(habit -> {
            System.out.println("User: " + habit.getUser());
            System.out.println("Habit Title: " + habit.getTitle());
            System.out.println("Category: " + habit.getCategory());
            System.out.println("Start Date: " + habit.getStartDate());
            System.out.println("Color: " + habit.getColor());
        });
        model.addAttribute("habits", habits);
        return "habitlist";
    }
}