<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ChanDo Profile</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="./css/Profile.css">
    <script src="/js/Profile.js" type="text/javascript"></script>
</head>
<body>
<div class="container">
    <!-- Header -->
    <div class="header">
        <a href="/mainpage" style="color: inherit; text-decoration: none;">
            <i class="fas fa-arrow-left"></i>
        </a>
        <h1>${Username}</h1>
        <i class="fas fa-ellipsis-v" id="menu-toggle"></i>
        <div class="dropdown-menu" id="dropdown-menu">
            <a href="/achievements"><i class="fas fa-trophy"></i>Достижения</a>
            <a href="/settings"><i class="fas fa-cog"></i>Настройки</a>
            <a href="/logout"><i class="fas fa-sign-out-alt"></i>Выйти</a>
        </div>
    </div>

    <!-- Profile Section -->
    <div class="profile-section">
        <div class="profile-background"></div>
        <div class="profile-icon" id="profile-icon"><img src="imgs/${Avatar}.png" alt="Selected Avatar" style="width: 100%; height: 100%; border-radius: 50%;"></div>
        <a href="level.html" class="level-button">
            <span>${Level}</span>
            <span>Уровень</span>
        </a>
        <p style="color: #666; margin-top: 8px;">Возраст ${Age}<span style="color: #00b4d8;"></span></p>
    </div>

    <!-- Achievements Section -->
    <div class="achievements">
        <div class="achievements-counter">
            Достижения ${Achievements} из 50
        </div>
        <div class="star-counter">
            <i class="fas fa-star"></i>
            ${Stars}
        </div>
    </div>

    <!-- Habits Section -->
    <div class="habits">
        <a href="/habitadd">
            <span>Добавление привычки</span>
            <div style="display: flex; align-items: center;">
                <i class="fas fa-chevron-right" style="margin-left: 8px;"></i>
            </div>
        </a>
        <a href="/habitlist">
            <span>Текущие привычки</span>
            <div style="display: flex; align-items: center;">
                <span class="badge badge-red">${HabitCol}</span>
                <i class="fas fa-chevron-right" style="margin-left: 8px;"></i>
            </div>
        </a>
    </div>
</div>

<!-- Modal for Avatar Selection -->
<div id="avatar-modal" class="modal">
    <div class="modal-content">
        <h2 class="section-title">Выберите аватарку</h2>
        <div id="avatars-grid" class="grid grid-cols-3 gap-4">
        </div>
    </div>
</div>
</body>
</html>