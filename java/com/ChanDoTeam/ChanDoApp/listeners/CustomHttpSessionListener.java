package com.ChanDoTeam.ChanDoApp.listeners;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@Component
@WebListener
public class CustomHttpSessionListener implements HttpSessionListener {

    @Value("${server.servlet.session.timeout}")
    private int sessionTimeout; // Таймаут в секундах

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        // Устанавливаем таймаут сессии
        event.getSession().setMaxInactiveInterval(sessionTimeout);
        System.out.println("Session created with timeout: " + sessionTimeout + " seconds");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("Session destroyed: " + event.getSession().getId());
    }
}