package com.ChanDoTeam.ChanDoApp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProfileController {

    // Эндпоинт для получения данных профиля
    @GetMapping("/profile")
    public Map<String, Object> getProfileData() {
        Map<String, Object> profileData = new HashMap<>();
        profileData.put("username", "Пользователь");
        profileData.put("level", 5);
        profileData.put("since", "января 2025");
        profileData.put("achievements", "12/50");
        profileData.put("stars", 350);

        return profileData;
    }
}