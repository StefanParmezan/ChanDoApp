package com.ChanDoTeam.ChanDoApp.controllers;

import com.ChanDoTeam.ChanDoApp.services.RegistrationResponse;
import com.ChanDoTeam.ChanDoApp.services.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {
    private final RegistrationService registrationService;
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model, CsrfToken csrfToken) {
        model.addAttribute("_csrf", csrfToken); // Добавляем CSRF-токен в модель
        return "Registration"; // имя HTML-шаблона для страницы регистрации
    }

    @PostMapping("/registration")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String confirmPassword,
                               @RequestParam byte date,
                               @RequestParam String email,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        RegistrationResponse response = registrationService.registerUser(username, password, confirmPassword, date, email);

        if (response.isSuccess()) {
            // Передаем имя пользователя и пароль через RedirectAttributes
            return "redirect:/login"; // Перенаправляем на страницу логина
        } else {
            // Передаем в модель только те данные, которые не должны очищаться
            model.addAttribute("username", username);
            model.addAttribute("email", email);
            model.addAttribute("date", date);
            model.addAttribute("errorMessage", response.getErrorMessage());
            return "Registration"; // Возвращаем на страницу регистрации с сообщением об ошибке
        }
    }
}