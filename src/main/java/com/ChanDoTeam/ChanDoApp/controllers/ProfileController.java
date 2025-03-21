package com.ChanDoTeam.ChanDoApp.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String showProfilePage() {
        return "Profile"; // Возвращает имя представления (profile.html или profile.jsp)
    }

    @GetMapping("/achievements")
    public String showAchievementsPage() {
        return "Achievements"; // achievements.html или achievements.jsp
    }

    @GetMapping("/settings")
    public String showSettingsPage() {
        return "Settings"; // settings.html или settings.jsp
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // Логика выхода из системы
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login"; // Перенаправление на страницу входа
    }
}
