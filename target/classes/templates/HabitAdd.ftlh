<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Добавление привычки</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <link href="/css/HabitAdd.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <script src="/js/HabitAdd.js" type="text/javascript"></script>
</head>
<body>
<div class="container">
    <!-- Заголовок -->
    <div class="header">
        <i class="fas fa-arrow-left text-white text-xl cursor-pointer" onclick="goBack()"></i>
        <h1>Добавление привычки</h1>
    </div>

    <!-- Иконка -->
    <div class="icon">
        <img id="icon-image" src="/imgs/WhiteBrain.png" alt="Icon of a brain"/>
    </div>

    <!-- Форма для создания привычки -->
    <form action="/habitadd" method="post" onsubmit="return validateAndSubmit()">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <!-- Поле для названия привычки -->
        <p class="text-left mb-2 text-sm">Мне нужно исключить из своей жизни</p>
        <div class="input-group mb-4">
            <input id="text-to-change" name="title" placeholder="Что вы хотите исключить" type="text" required>
            <i class="fas fa-pen text-white ml-2"></i>
        </div>

        <!-- Выбор категории -->
        <label class="dff" for="exclude-options">Категория цели:</label>
        <select id="exclude-options" name="category" required>
            <option value="Вредная">Вредная</option>
            <option value="Полезная">Полезная</option>
        </select>

        <!-- Ввод даты -->
        <p class="text-left mb-2 text-sm">Я начну с:</p>
        <div class="date-group mb-4">
            <input id="day-input" name="day" placeholder="ДД" type="number"
                   min="1" max="31" required class="no-spinner" value="${day!''}" readonly>
            <input id="month-input" name="month" placeholder="ММ" type="number"
                   min="1" max="12" required class="no-spinner" value="${month!''}" readonly>
            <input id="year-input" name="year" placeholder="ГГГГ" type="number"
                   min="2024" required class="no-spinner" value="${year!''}" readonly>
            <i class="fas fa-calendar-alt text-white ml-2"></i>
        </div>

        <!-- Скрытое поле для отправки даты -->
        <input type="date" id="start-date" name="startDate" hidden>

        <!-- Выбор цвета -->
        <p class="text-left mb-6 text-sm">Подходящий цвет</p>
        <div class="color-group mb-6">
            <div class="color-option" style="background-color: #f87171;" onclick="changeTextColor('#f87171', this, '/imgs/RedBrain.png', 'RedBrain')"></div>
            <div class="color-option" style="background-color: #fb923c;" onclick="changeTextColor('#fb923c', this, '/imgs/OrangeBrain.png', 'OrangeBrain')"></div>
            <div class="color-option" style="background-color: #facc15;" onclick="changeTextColor('#facc15', this, '/imgs/YellowBrain.png', 'YellowBrain')"></div>
            <div class="color-option" style="background-color: #4ade80;" onclick="changeTextColor('#4ade80', this, '/imgs/GreenBrain.png', 'GreenBrain')"></div>
            <div class="color-option" style="background-color: #22d3ee;" onclick="changeTextColor('#22d3ee', this, '/imgs/LightBlue.png', 'LightBlueBrain')"></div>
            <div class="color-option" style="background-color: #3b82f6;" onclick="changeTextColor('#3b82f6', this, '/imgs/BlueBrain.png', 'BlueBrain')"></div>
            <div class="color-option" style="background-color: #a855f7;" onclick="changeTextColor('#a855f7', this, '/imgs/PurpleBrain.png', 'PurpleBrain')"></div>
            <div class="color-option" style="background-color: #ec4899;" onclick="changeTextColor('#ec4899', this, '/imgs/PinkBrain.png', 'PinkBrain')"></div>
        </div>

        <!-- Скрытое поле для выбранного цвета -->
        <input type="hidden" id="selected-color" name="color" value="WhiteBrain">

        <!-- Кнопка отправки формы -->
        <div class="button-group">
            <button type="submit">Начать</button>
        </div>
    </form>

    <!-- Модальное окно -->
    <div id="error-modal" class="modal" style="display: none;">
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <p id="error-message"></p>
        </div>
    </div>
</div>
</body>
</html>