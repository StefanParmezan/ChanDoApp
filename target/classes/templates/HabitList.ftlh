<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Список привычек</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="./css/HabitList.css"> <!-- Подключение вашего CSS -->
    <script src="/js/HabitList.js" type="text/javascript"></script>
</head>
<body>
<div class="container">
  <div class="header">
    <a href="/mainpage" class="fa-bars-icon"><i class="fas fa-bars menu-icon"></i></a>
    <div class="search-bar">
      <i class="fas fa-search"></i>
      <label>
        <input id="search-input" type="text" placeholder="ПОИСК">
      </label>
    </div>
    <div class="points">
      <span class="fas fa-star">${totalStars}</span>
    </div>
  </div>

  <div class="container">
    <#list habits as habit>
      <div class="card" data-start-date="${habit.notificationTime!'N/A'}" data-last-completed="${habit.lastCompleted!'0'}">
        <div class="progress-circle" style="background-color: ${habit.color!'white'};">
          <img class = "brain-icon" src="/imgs/${habit.color}.png" alt="Habit Icon">
        </div>
        <div class="details">
          <h3>${habit.title!"N/A"}</h3>
          <div class="stats">
            <div>
              <span class="ppoo">Старт</span>
              <span style="color: #00aaff">${habit.getFormattedVisibleDate()!"N/A"}</span>
            </div>
            <div>
              <span class="pop">Стрик</span>
              <span class="pdpd" style="color: #00aaff">${habit.streak!'0'}</span>
            </div>
            <div>
              <span>Категория</span>
              <span style="color: #00aaff">${habit.category!"N/A"}</span>
            </div>
          </div>
        </div>
      </div>
    </#list>
  </div>
  <a href="/habitadd">
    <div class="add-button">
      <i class="fas fa-plus"></i>
    </div>
  </a>
</div>

</body>

</html>