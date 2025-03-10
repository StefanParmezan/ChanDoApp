package com.ChanDoTeam.ChanDoApp.services;

import com.ChanDoTeam.ChanDoApp.models.Habit;
import com.ChanDoTeam.ChanDoApp.repositories.HabitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitListService {

    @Autowired
    private HabitRepository habitRepository;

    // Получить все привычки по userId
    public List<Habit> getHabitsByUserId(Long userId) {
        return habitRepository.findByUserId(userId);
    }

    // Получить привычки по userId и категории
    public List<Habit> getHabitsByUserIdAndCategory(Long userId, String category) {
        return habitRepository.findByUserIdAndCategory(userId, category);
    }
    private static final Logger logger = LoggerFactory.getLogger(HabitListService.class);
}