package com.ChanDoTeam.ChanDoApp.services;

import com.ChanDoTeam.ChanDoApp.models.Habit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.HashMap;
import java.util.Map;

@Service
public class TelegramNotifier {
    private static final Logger logger = LoggerFactory.getLogger(TelegramNotifier.class);
    private static final String BOT_TOKEN = "7619304652:AAFndA7Yu8VfSA139YOSwqv3tDj1mRNcCIs";
    private static final String TELEGRAM_API_URL = "https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d MMMM yyyy")
            .withLocale(new Locale("ru"));
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private final RestTemplate restTemplate;

    public TelegramNotifier(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendHabitAddedNotification(String telegramId, Habit habit) {
        try {
            String message = String.format(
                    "🎉 Ваша %s привычка\\: *%s* \nБыла добавлена %s и успешно подключена к боту\\!%n%n" +
                            "Теперь вы будете получать ежедневные напоминания в это время",
                    escapeMarkdown(habit.getCategory().toLowerCase()),
                    escapeMarkdown(habit.getTitle()),
                    escapeMarkdown(habit.getVisibleDate().format(DATE_FORMATTER))
            );

            logger.debug("Raw message before sending: {}", message);
            sendTelegramMessage(telegramId, message);
        } catch (Exception e) {
            logger.error("Failed to send habit added notification to {}: {}", telegramId, e.getMessage(), e);
        }
    }

    public void sendHabitReminder(String telegramId, Habit habit) {
        try {
            String message = String.format(
                    "⏰ *Напоминание о привычке\\!*%n%n" +
                            "🔹 *Привычка\\:* %s%n" +
                            "📅 *Дата начала\\:* %s%n" +
                            "🔥 *Дней подряд\\:* %d%n",
                    escapeMarkdown(habit.getTitle()),
                    escapeMarkdown(habit.getVisibleDate().format(DATE_FORMATTER)),
                    habit.getStreak()
            );

            sendTelegramMessage(telegramId, message);
        } catch (Exception e) {
            logger.error("Failed to send habit reminder to {}: {}", telegramId, e.getMessage(), e);
        }
    }

    private void sendTelegramMessage(String telegramId, String message) {
        try {
            // Создаем тело запроса
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("chat_id", telegramId);
            requestBody.put("text", message);
            requestBody.put("parse_mode", "MarkdownV2");

            // Устанавливаем заголовки
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Формируем HTTP-сущность
            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

            // Отправляем POST-запрос
            String response = restTemplate.postForObject(TELEGRAM_API_URL, requestEntity, String.class);
            logger.debug("Telegram API response: {}", response);
        } catch (Exception e) {
            logger.error("Failed to send Telegram message to {}: {}", telegramId, e.getMessage(), e);
            throw new RuntimeException("Failed to send Telegram message to " + telegramId, e);
        }
    }

    private String escapeMarkdown(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        // Экранируем все специальные символы MarkdownV2
        return text.replace("_", "\\_")
                .replace("*", "\\*")
                .replace("[", "\\[")
                .replace("]", "\\]")
                .replace("(", "\\(")
                .replace(")", "\\)")
                .replace("~", "\\~")
                .replace("`", "\\`")
                .replace(">", "\\>")
                .replace("#", "\\#")
                .replace("+", "\\+")
                .replace("-", "\\-")
                .replace("=", "\\=")
                .replace("|", "\\|")
                .replace("{", "\\{")
                .replace("}", "\\}")
                .replace(".", "\\.")
                .replace("!", "\\!")
                .replace(":", "\\:")
                .replace("\\", "\\\\");
    }
}