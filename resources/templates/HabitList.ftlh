<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Список привычек</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
  <link href="/css/HabitList.css" rel="stylesheet">
</head>
<body>
<div class="container">
  <div class="header">
    <a href="/mainpage" class="fa-bars-icon"><i class="fas fa-bars menu-icon"></i></a>
    <div class="search-bar">
      <i class="fas fa-search"></i>
      <label>
        <input type="text" placeholder="ПОИСК">
      </label>
    </div>
    <div class="points">
      <i class="fas fa-star"></i>0
    </div>
  </div>

  <#if habits?has_content>
    <#list habits as habit>
      <#if habit.title?has_content && habit.startDate?has_content && habit.category?has_content && habit.color?has_content>
        <div class="card cardd">
          <div class="progress-circle" style="background-color: ${habit.color!'white'};">
            <img src="/imgs/${habit.color}.png" alt="Habit Icon">
          </div>
          <div class="details">
            <h3>${habit.title}</h3>
            <div class="stats">
              <div>
                <span class="ppoo">Старт</span>
                <span style="color: #00aaff">${habit.startDate}</span>
              </div>
              <div>
                <span class="pop">Стрик</span>
                <span class="pdpd" style="color: #00aaff">1</span>
              </div>
              <div>
                <span>Категория</span>
                <span style="color: #00aaff">${habit.category}</span>
              </div>
            </div>
          </div>
        </div>
      </#if>
    </#list>
  <#else>
    <p>Ведется  разработка над отображением привычек на этой странице, но привычки уже сохраняются в базу данных</p>
  </#if>

  <a href="/habitadd">
    <div class="add-button">
      <i class="fas fa-plus"></i>
    </div>
  </a>

  <script src="/js/HabitList.js" type="text/javascript"></script>
</div>
</body>
</html>