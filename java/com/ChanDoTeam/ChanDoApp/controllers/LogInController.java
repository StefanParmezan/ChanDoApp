package com.ChanDoTeam.ChanDoApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogInController {

    @GetMapping("/login")
    public String showLogInForm(Model model, CsrfToken csrfToken,
                                @RequestParam(required = false) String username,
                                @RequestParam(required = false) String password) {
        model.addAttribute("_csrf", csrfToken); // Передаем CSRF-токен в модель

        // Если переданы имя пользователя и пароль, добавляем их в модель
        if (username != null && password != null) {
            model.addAttribute("username", username);
            model.addAttribute("password", password);
        }

        return "LogIn"; // Возвращаем имя шаблона для страницы логина
    }
}