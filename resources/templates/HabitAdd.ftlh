<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <title>Добавление привычек</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="./css/HabitAdd.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet"/>
</head>
<body>
    <div class="container">
        <div class="header">
            <i class="fas fa-arrow-left text-white text-xl cursor-pointer" onclick="goBack()"></i>
            <h1>Добавление привычки</h1>
        </div>
        <div class="icon">
            <img id="icon-image" src="./imgs/WhiteBrain.png" alt="Icon of a brain"/>
        </div>
        <p class="text-left mb-2 text-sm">Мне нужно исключить из своей жизни</p>
        <div class="input-group mb-4">
            <input id="text-to-change" placeholder="Что вы хотите исключить" type="text"/>
            <i class="fas fa-pen text-white ml-2"></i>
        </div>
        <div class="dicc">
            <label class="dff" for="exclude-options">Категория цели:</label>
            <select id="exclude-options">
              <option value="option1">Вредная</option>
              <option value="option2">Полезная</option>
            </select>
          </div>
        <p class="text-left mb-2 text-sm">Я начну с:</p>
        <div class="date-group mb-4">
            <input id="day-input" placeholder="ДД" type="text"/>
            <input id="month-input" placeholder="ММ" type="text"/>
            <input id="year-input" placeholder="ГГГГ" type="text"/>
            <i class="fas fa-calendar-alt text-white ml-2" onclick="openCalendar()"></i>
        </div>
        <p id="error-message" class="error-message"></p>
        <p class="text-left mb-6 text-sm">Подходящий цвет</p>
        <div class="color-group mb-6">
            <div class="color-option" style="background-color: #f87171;" onclick="changeTextColor('#f87171', this, './imgs/RedBrain.png')"></div>
            <div class="color-option" style="background-color: #fb923c;" onclick="changeTextColor('#fb923c', this, './imgs/OrangeBrain.png')"></div>
            <div class="color-option" style="background-color: #facc15;" onclick="changeTextColor('#facc15', this, './imgs/YellowBrain.png')"></div>
            <div class="color-option" style="background-color: #4ade80;" onclick="changeTextColor('#4ade80', this, './imgs/GreenBrain.png')"></div>
            <div class="color-option" style="background-color: #22d3ee;" onclick="changeTextColor('#22d3ee', this, './imgs/LightBlue.png')">
                <i class="fas fa-check text-black"></i>
            </div>
            <div class="color-option" style="background-color: #3b82f6;" onclick="changeTextColor('#3b82f6', this, './imgs/BlueBrain.png')"></div>
            <div class="color-option" style="background-color: #a855f7;" onclick="changeTextColor('#a855f7', this, './imgs/PurpleBrain.png')"></div>
            <div class="color-option" style="background-color: #ec4899;" onclick="changeTextColor('#ec4899', this, './imgs/RedBrain.png')"></div>
        </div>
        <div class="button-group">
            <button onclick="navigateToPage()">Начать</button>
        </div>
    </div>
    <script src="./js/HabitAdd.js"></script>
</body>
</html>