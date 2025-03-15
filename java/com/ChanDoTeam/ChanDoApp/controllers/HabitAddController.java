package com.ChanDoTeam.ChanDoApp.controllers;

import com.ChanDoTeam.ChanDoApp.models.Habit;
import com.ChanDoTeam.ChanDoApp.models.User;
<<<<<<< HEAD
import com.ChanDoTeam.ChanDoApp.repositories.HabitRepository;
import com.ChanDoTeam.ChanDoApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class HabitAddController {
    private final UserService userService;
    private final HabitRepository habitRepository;

    @Autowired
    public HabitAddController(UserService userService, HabitRepository habitRepository) {
        this.userService = userService;
        this.habitRepository = habitRepository;
    }

    @GetMapping("/habitadd")
    public String showAddForm(Model model) {
        LocalDate today = LocalDate.now();
        model.addAttribute("day", today.getDayOfMonth());
        model.addAttribute("month", today.getMonthValue());
        model.addAttribute("year", today.getYear());

        return "habitadd";
    }

    @PostMapping("/habitadd")
    public String addHabit(
            @RequestParam int day,
            @RequestParam int month,
            @RequestParam int year,
            @ModelAttribute Habit habit,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        User user = userService.getUserByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println("Day: " + day);
        System.out.println("Month: " + month);
        System.out.println("Year: " + year);
        // Объединяем день, месяц и год в LocalDate
        LocalDate startDate = LocalDate.of(year, month, day);
        habit.setStartDate(startDate);

        habit.setUser(user);
        habitRepository.save(habit);

        return "redirect:/habitlist";
    }
=======
import com.ChanDoTeam.ChanDoApp.services.HabitAddService;
import com.ChanDoTeam.ChanDoApp.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/habitadd")
@RequiredArgsConstructor
public class HabitAddController {

    private final HabitAddService habitAddService;
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(HabitAddController.class);

    @GetMapping
    public String showAddForm(Model model, CsrfToken csrfToken) {
        model.addAttribute("habit", new Habit());
        model.addAttribute("_csrf", csrfToken);
        return "HabitAdd";
    }

    @PostMapping
    public String addHabit(
            @Valid @ModelAttribute Habit habit,
            BindingResult result,
            @AuthenticationPrincipal UserDetails userDetails,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            return "HabitAdd";
        }

        User user = userService.getUserByUsername(userDetails.getUsername())
                .orElseThrow(() -> {
                    logger.error("User not found: {}", userDetails.getUsername());
                    return new IllegalArgumentException("User not found");
                });

        habitAddService.addHabit(habit, user);
        logger.info("New habit added: {}", habit.getTitle());
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/habitlist";
    }

    @ExceptionHandler({IllegalArgumentException.class, DataAccessException.class})
    public String handleExceptions(Exception ex, RedirectAttributes redirectAttributes) {
        logger.error("Error occurred: {}", ex.getMessage());
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        return "redirect:/error";
    }
>>>>>>> b80cf8db0ed71f2ad1f64892d866a61570165012
}