package com.ChanDoTeam.ChanDoApp.services;

import com.ChanDoTeam.ChanDoApp.models.Habit;
import com.ChanDoTeam.ChanDoApp.models.User;
import com.ChanDoTeam.ChanDoApp.repositories.HabitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class HabitAddService {

    @Autowired
    private HabitRepository habitRepository;

    public void addHabit(Habit habit, User user) {
        if (habitRepository.existsByTitleAndUserId(habit.getTitle(), user.getId())) {
            throw new IllegalArgumentException("Привычка уже существует");
        }
        habit.setUser(user);
        habitRepository.save(habit);
    }
    private static final Logger logger = LoggerFactory.getLogger(HabitAddService.class);
}