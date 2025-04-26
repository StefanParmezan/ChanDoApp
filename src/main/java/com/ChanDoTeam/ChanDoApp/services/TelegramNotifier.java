package com.ChanDoTeam.ChanDoApp.services;

import com.ChanDoTeam.ChanDoApp.models.Habit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class TelegramNotifier {
    private static final Logger logger = LoggerFactory.getLogger(TelegramNotifier.class);

    private final RestTemplate restTemplate;
    private final String BOT_TOKEN = "7619304652:AAFndA7Yu8VfSA139YOSwqv3tDj1mRNcCIs";
    private final String TELEGRAM_API_URL = "https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage";

    public TelegramNotifier(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendHabitAddedNotification(String  telegramId, Habit habit) {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy")
                    .withLocale(new Locale("ru"));

            String message = String.format(
                    "🎉 Ваша привычка: *%s* была добавлена: %s и успешно подключена к боту!",
                    habit.getTitle(),
                    habit.getVisibleDate().format(dateFormatter)
            );

            sendTelegramMessage(telegramId, message);
        } catch (Exception e) {
            logger.error("Failed to send habit added notification to {}: {}", telegramId, e.getMessage(), e);
        }
    }

    public void sendHabitReminder(String telegramId, Habit habit) {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy")
                    .withLocale(new Locale("ru"));

            String message = String.format(
                    "⏰ *Напоминание о привычке!*%n%n" +
                            "🔹 *Привычка:* %s%n" +
                            "📅 *Дата начала:* %s%n" +
                            "🔥 *Дней подряд:* %d%n" +
                            "⏱ *Время уведомления:* %s",
                    habit.getTitle(),
                    habit.getVisibleDate().format(dateFormatter),
                    habit.getStreak(),
                    habit.getNotificationTime().format(DateTimeFormatter.ofPattern("HH:mm"))
            );

            sendTelegramMessage(telegramId, message);
        } catch (Exception e) {
            logger.error("Failed to send habit reminder to {}: {}", telegramId, e.getMessage(), e);
        }
    }

    private void sendTelegramMessage(String telegramId, String message) {
        try {
            String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8.toString());
            String url = String.format("%s?chat_id=%d&text=%s",
                    TELEGRAM_API_URL,
                    telegramId,
                    encodedMessage);

            logger.debug("Sending Telegram message to {}: {}", telegramId, message);
            String response = restTemplate.getForObject(url, String.class);
            logger.debug("Telegram API response: {}", response);
        } catch (Exception e) {
            logger.error("Failed to send Telegram message: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to send Telegram message", e);
        }
    }
}