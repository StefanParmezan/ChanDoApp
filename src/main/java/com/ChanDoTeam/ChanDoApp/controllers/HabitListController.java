package com.ChanDoTeam.ChanDoApp.controllers;

import com.ChanDoTeam.ChanDoApp.models.Habit;
import com.ChanDoTeam.ChanDoApp.models.User;
import com.ChanDoTeam.ChanDoApp.repositories.HabitRepository;
import com.ChanDoTeam.ChanDoApp.services.HabitListService;
import com.ChanDoTeam.ChanDoApp.services.StreakService;
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
    private final StreakService streakService;
    private final UserService userService;

    @GetMapping("/habitlist")
    public String showHabits(Model model, Authentication authentication) {
        // Получаем текущего пользователя
        String username = authentication.getName();
        User user = userService.getUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Обновляем стрик и звезды
        streakService.updateStreakForUser(user);

        // Получаем список привычек
        List<Habit> habits = habitListService.getHabitsByUserId(user.getId());

        // Передаем данные в модель
        model.addAttribute("habits", habits);
        model.addAttribute("totalStars", user.getTotalStars());

        return "habitlist";
    }
}