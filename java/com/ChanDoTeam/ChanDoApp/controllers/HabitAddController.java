package com.ChanDoTeam.ChanDoApp.controllers;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HabitAddController {

    @GetMapping("/habitadd")
    public String Habit(Model model) {
        CsrfToken token = (CsrfToken) model.getAttribute("_csrf");
        return "HabitAdd";
    }


}
