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
import com.ChanDoTeam.ChanDoApp.models.User;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
public class RegistrationController {
    private final RegistrationService registrationService;
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    public RegistrationController(RegistrationService RegistrationService) {
        this.registrationService = RegistrationService;
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
                               @RequestParam int age,
                               @RequestParam String telegramId,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        RegistrationResponse response = registrationService.registerUser(username, password, confirmPassword, age, telegramId);
        if (response.isSuccess()) {
            // Формируем дату регистрации в нужном формате


            // Передаём дату регистрации в сервис или модель User
            User registeredUser = new User();
            registeredUser.setUsername(username);
            registeredUser.setPassword(password); // Убедитесь, что пароль хранится безопасно (хэширование)
            registeredUser.setAge(age);
            registeredUser.setTelegramId(telegramId);


            // Логируем успешную регистрацию



            // Перенаправляем на страницу логина
            return "redirect:/login";
        } else {
            // Передаем в модель только те данные, которые не должны очищаться
            model.addAttribute("username", username);
            model.addAttribute("telegramId", telegramId);
            model.addAttribute("age", age);
            model.addAttribute("errorMessage", response.getErrorMessage());
            return "Registration"; // Возвращаем на страницу регистрации с сообщением об ошибке
        }
    }

    /**
     * Метод для форматирования даты регистрации в формате "месяца год".
     * Например: "января 2025".
     *
     * @param month месяц (1-12)
     * @param year год
     * @return отформатированная строка даты
     */
    private String formatRegistrationDate(int month, int year) {
        // Получаем название месяца в именительном падеже
        String monthName = LocalDate.of(year, month, 1)
                .getMonth()
                .getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru"))
                .toLowerCase();

        // Список месяцев, которые требуют особого склонения
        Map<String, String> specialCases = new HashMap<>();
        specialCases.put("март", "марта");
        specialCases.put("август", "августа");

        // Если месяц есть в списке исключений, используем его склоненную форму
        if (specialCases.containsKey(monthName)) {
            monthName = specialCases.get(monthName);
        } else {
            // Для остальных месяцев добавляем окончание "-а"
            monthName = monthName.substring(0, monthName.length() - 1) + "я";
        }

        // Формируем итоговую строку
        return monthName + " " + year;
    }
}