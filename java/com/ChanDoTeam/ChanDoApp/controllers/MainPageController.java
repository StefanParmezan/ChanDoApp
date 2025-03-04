package com.ChanDoTeam.ChanDoApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping("/mainpage")
    public String showMainPage() {
        return "MainPage"; // имя HTML-шаблона для главной страницы
    }
}