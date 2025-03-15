package com.ChanDoTeam.ChanDoApp.controllers;

import com.ChanDoTeam.ChanDoApp.models.Habit;
import com.ChanDoTeam.ChanDoApp.models.User;
<<<<<<< HEAD
import com.ChanDoTeam.ChanDoApp.repositories.HabitRepository;
=======
>>>>>>> b80cf8db0ed71f2ad1f64892d866a61570165012
import com.ChanDoTeam.ChanDoApp.services.HabitListService;
import com.ChanDoTeam.ChanDoApp.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.security.core.Authentication;
=======
>>>>>>> b80cf8db0ed71f2ad1f64892d866a61570165012
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD

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
=======
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class HabitListController {

    @Autowired
    private HabitListService habitListService;

    @Autowired
    private UserService userService;

    // Отображение списка привычек
    @GetMapping("/habitlist")
    public String habitListPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();

        // Получаем пользователя
        Optional<User> userOptional = userService.getUserByUsername(username);

        if (!userOptional.isPresent()) { // Используем isPresent() вместо isEmpty() для Java 8+ совместимости
            return "redirect:/error"; // Перенаправление на страницу ошибки
        }

        User user = userOptional.get();
        List<Habit> habits = habitListService.getHabitsByUserId(user.getId());

        model.addAttribute("habits", habits);
        return "HabitList";
    }
    private static final Logger logger = LoggerFactory.getLogger(HabitListController.class);
>>>>>>> b80cf8db0ed71f2ad1f64892d866a61570165012
}