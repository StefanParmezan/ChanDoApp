package com.ChanDoTeam.ChanDoApp.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HabitController {

    @GetMapping("/Habit")
    public String Habit(Model model) {
        CsrfToken token = (CsrfToken) model.getAttribute("_csrf");
        return "Habit";
    }


}
