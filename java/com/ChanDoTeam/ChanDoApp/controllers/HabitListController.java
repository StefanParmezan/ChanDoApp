package com.ChanDoTeam.ChanDoApp.controllers;

import com.ChanDoTeam.ChanDoApp.models.Habit;
import com.ChanDoTeam.ChanDoApp.models.User;
import com.ChanDoTeam.ChanDoApp.services.HabitListService;
import com.ChanDoTeam.ChanDoApp.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}