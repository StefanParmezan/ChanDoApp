package com.ChanDoTeam.ChanDoApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class LogInController {

    private static final Logger logger = LoggerFactory.getLogger(LogInController.class);

    @GetMapping("/login")
    public String showLogInForm(Model model, CsrfToken csrfToken,
                                @RequestParam(required = false) String username,
                                @RequestParam(required = false) String password,
                                @RequestParam(required = false) String error,
                                @RequestParam(required = false) String expired) {
        logger.info("Login page requested. Expired: {}", expired);

        model.addAttribute("_csrf", csrfToken); // Передаем CSRF-токен в модель

        if (username != null && password != null) {
            model.addAttribute("username", username);
            model.addAttribute("password", password);
        }

        if (error != null) {
            model.addAttribute("error", "Неверное имя пользователя или пароль.");
        }

        if (expired != null) {
            model.addAttribute("expired", "Ваша сессия истекла. Пожалуйста, войдите снова.");
        }

        return "LogIn"; // Возвращаем имя шаблона для страницы логина
    }
}