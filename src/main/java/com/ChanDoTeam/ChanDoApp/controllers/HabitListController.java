package com.ChanDoTeam.ChanDoApp.controllers;

import com.ChanDoTeam.ChanDoApp.models.Habit;
import com.ChanDoTeam.ChanDoApp.models.User;
import com.ChanDoTeam.ChanDoApp.repositories.HabitRepository;
import com.ChanDoTeam.ChanDoApp.services.HabitListService;
import com.ChanDoTeam.ChanDoApp.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HabitListController {

    private final HabitListService habitListService;
    private final UserService userService;
    private final HabitRepository habitRepository;

    @GetMapping("/habitlist")
    public String showHabits(Model model, Authentication authentication) {
        // Получаем текущего аутентифицированного пользователя
        String username = authentication.getName();
        log.info("Loading habits for user: {}", username);

        // Находим пользователя в базе данных
        User user = userService.getUserByUsername(username)
                .orElseThrow(() -> {
                    log.error("User not found: {}", username);
                    return new RuntimeException("User not found");
                });

        // Получаем список привычек пользователя
        List<Habit> habits = habitRepository.findByUser(user);
        log.info("Loaded {} habits for user: {}", habits.size(), username);

        // Передаем данные в шаблон
        model.addAttribute("habits", habits);
        return "habitlist";
    }
}