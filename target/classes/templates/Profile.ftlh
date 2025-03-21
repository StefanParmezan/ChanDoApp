<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ChanDo Profile</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="/css/Profile.css">
</head>
<body>
<div class="container">
    <!-- Header -->
    <header class="header">
        <div class="header__navigation">
            <a href="/mainpage" class="header__back-link" style="color: inherit; text-decoration: none;">
                <i class="fas fa-arrow-left"></i>
            </a>
            <h1 class="header__title">Пользователь</h1>
        </div>
        <nav class="menu-links">
            <a href="/achievements" class="menu-links__item">
                <i class="fas fa-trophy"></i> Достижения
            </a>
            <a href="/settings" class="menu-links__item">
                <i class="fas fa-cog"></i> Настройки
            </a>
            <a href="/logout" class="menu-links__item">
                <i class="fas fa-sign-out-alt"></i> Выйти
            </a>
        </nav>
    </header>

    <!-- Profile Section -->
    <section class="profile-section">
        <div class="profile-section__background"></div>
        <div class="profile-section__icon">П</div>
        <a href="/level" class="profile-section__level-button">
            <span class="profile-section__level-number">5</span>
            <span class="profile-section__level-text">Уровень</span>
        </a>
        <p class="profile-section__join-date">
            В ChanDo с <span class="profile-section__date-highlight">января 2025</span>
        </p>
    </section>

    <!-- Achievements Section -->
    <section class="achievements">
        <div class="achievements__counter">
            Достижения 12/50
        </div>
        <div class="achievements__star-counter">
            <i class="fas fa-star"></i>
            <span class="achievements__star-count">350</span>
        </div>
    </section>

    <!-- Habits Section -->
    <section class="habits">
        <a href="/habitadd" class="habits__item">
            <span class="habits__item-text">Добавление привычки</span>
            <div class="habits__item-icon">
                <i class="fas fa-chevron-right"></i>
            </div>
        </a>
        <a href="/habitlist" class="habits__item">
            <span class="habits__item-text">Текущие привычки</span>
            <div class="habits__item-badge-container">
                <span class="badge badge-red habits__item-badge">2</span>
                <i class="fas fa-chevron-right habits__item-icon"></i>
            </div>
        </a>
    </section>
</div>
</body>
</html>