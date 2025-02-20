package com.ChanDoTeam.ChanDoApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/registration", "/css/**", "/js/**", "/imgs/**").permitAll() // Разрешить доступ к публичным ресурсам
                        .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
                )
                .formLogin(form -> form
                        .loginPage("/login") // Страница входа
                        .defaultSuccessUrl("/mainPage", true) // Перенаправление после успешного входа
                        .failureUrl("/login?error=true") // Перенаправление при ошибке
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login") // Перенаправление после выхода
                        .permitAll()
                );

        return http.build();
    }
}