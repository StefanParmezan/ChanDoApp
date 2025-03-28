package com.ChanDoTeam.ChanDoApp.controllers;

import com.ChanDoTeam.ChanDoApp.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.ChanDoTeam.ChanDoApp.models.User;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
@Controller
@Slf4j
public class MainPageController {
    private final UserService userService;

    @GetMapping("/mainpage")
    public String showMainPage(Model model, Authentication authentication) {

        String username = authentication.getName();
        User user = userService.getUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("Avatar", user.getAvatar());

        return "MainPage"; // имя HTML-шаблона для главной страницы
    }
}