package com.ChanDoTeam.ChanDoApp.controllers;

import com.ChanDoTeam.ChanDoApp.repositories.UserRepository;
import com.ChanDoTeam.ChanDoApp.services.HabitListService;
import com.ChanDoTeam.ChanDoApp.services.StreakService;
import com.ChanDoTeam.ChanDoApp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.ChanDoTeam.ChanDoApp.models.*;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
public class ProfileController {
    private final HabitListService habitListService;
    private final StreakService streakService;
    private final UserService userService;

    @GetMapping("/profile")
    public String showProfilePage(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.getUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Habit> habits = habitListService.getHabitsByUserId(user.getId());

        model.addAttribute("Achievements", user.getTotalachivements());
        model.addAttribute("HabitCol", habits.size());
        model.addAttribute("Username", username);
        model.addAttribute("Level", user.getLevel());
        model.addAttribute("Stars", user.getTotalStars());
        model.addAttribute("Age", user.getAge());
        model.addAttribute("Avatar", user.getAvatar());

        System.out.println("User Avatar: " + user.getAvatar());
        return "Profile";
    }

    @PostMapping("/update-avatar")
    @ResponseBody
    public String updateAvatar(@RequestBody AvatarUpdateRequest request, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.getUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Обновляем аватар пользователя
        user.setAvatar("avatar" + (request.getAvatarIndex() + 1)); // avatar1, avatar2 и т.д.

        return "{\"status\":\"success\", \"avatar\":\"" + user.getAvatar() + "\"}";
    }

    // Другие методы остаются без изменений
    @GetMapping("/achievements")
    public String showAchievementsPage() {
        return "Achievements";
    }

    @GetMapping("/settings")
    public String showSettingsPage() {
        return "Settings";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }

    // Внутренний класс для обработки запроса
    private static class AvatarUpdateRequest {
        private int avatarIndex;

        // Геттеры и сеттеры
        public int getAvatarIndex() {
            return avatarIndex;
        }

        public void setAvatarIndex(int avatarIndex) {
            this.avatarIndex = avatarIndex;
        }
    }
}