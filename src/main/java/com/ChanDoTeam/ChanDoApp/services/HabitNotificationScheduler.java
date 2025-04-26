package com.ChanDoTeam.ChanDoApp.services;

import com.ChanDoTeam.ChanDoApp.models.Habit;
import com.ChanDoTeam.ChanDoApp.models.User;
import com.ChanDoTeam.ChanDoApp.repositories.HabitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class HabitNotificationScheduler {
    private static final Logger logger = LoggerFactory.getLogger(HabitNotificationScheduler.class);

    private final HabitRepository habitRepository;
    private final TelegramNotifier telegramNotifier;

    public HabitNotificationScheduler(HabitRepository habitRepository,
                                      TelegramNotifier telegramNotifier) {
        this.habitRepository = habitRepository;
        this.telegramNotifier = telegramNotifier;
    }

    @Scheduled(cron = "0 * * * * ?")
    public void checkAndSendNotifications() {
        LocalTime currentTime = LocalTime.now().withSecond(0);
        LocalDate today = LocalDate.now();

        logger.info("Starting notification check for time {}", currentTime);

        try {
            habitRepository.findAll().forEach(habit -> {
                try {
                    if (!habit.isNotifiedToday()) {
                        processHabitNotification(habit, currentTime, today);
                    }
                } catch (Exception e) {
                    logger.error("Error processing habit {}: {}", habit.getId(), e.getMessage(), e);
                }
            });
        } catch (Exception e) {
            logger.error("Error in notification scheduler: {}", e.getMessage(), e);
        }
    }

    private void processHabitNotification(Habit habit, LocalTime currentTime, LocalDate today) {
        LocalTime notificationTime = habit.getNotificationTime();

        if (habit.getVisibleDate().equals(today) &&
                (notificationTime.isBefore(currentTime) || notificationTime.equals(currentTime))) {
            sendNotification(habit, "new");
        }
        else if (notificationTime.equals(currentTime)) {
            sendNotification(habit, "scheduled");
        }
    }

    private void sendNotification(Habit habit, String type) {
        try {
            User user = habit.getUser();
            if (user == null) {
                logger.warn("No user found for habit {}", habit.getId());
                return;
            }

            telegramNotifier.sendHabitReminder(user.getTelegramId(), habit);

            habit.setNotifiedToday(true);
            habit.setStreak(habit.getStreak() + 1);
            habitRepository.save(habit);

            logger.info("{} notification sent for habit {} to user {}",
                    type, habit.getTitle(), user.getId());
        } catch (Exception e) {
            logger.error("Failed to send {} notification for habit {}: {}",
                    type, habit.getId(), e.getMessage(), e);
        }
    }
}