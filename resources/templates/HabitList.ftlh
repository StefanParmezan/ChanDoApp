<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link
          rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
  />
  <link
          href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap"
          rel="stylesheet"
  />
  <link rel="stylesheet" href="./css/HabitList.css" />
</head>
<body>
<div class="container">
  <div class="header">
    <a href="/mainpage" class="fa-bars-icon"
    ><i class="fas fa-bars menu-icon"></i
      ></a>
    <div class="search-bar">
      <i class="fas fa-search"></i>
      <input type="text" placeholder="ПОИСК" />
    </div>
    <div class="points">
      <i class="fas fa-star"></i>
      0
    </div>
  </div>
  <a href="page1.html" class="card cardd">
    <div class="progress-circle blue">
      <img src="./imgs/BlueBrain.png" alt="Brain Icon" class="brain-icon" />
    </div>
    <div class="details">
      <h3>Алкоголь</h3>
      <div class="stats">
        <div>
          <span class="ppoo">Старт</span>
          <span style="color: #00aaff">20.20.2020</span>
        </div>
        <div>
          <span class="pop">Стрик</span>
          <span class="pdpd" style="color: #00aaff">1</span>
        </div>
        <div>
          <span>Категория</span>
          <span style="color: #00aaff">Вредная</span>
        </div>
      </div>
    </div>
  </a>
</div>
<a href="/habitadd">
  <div class="add-button">
    <i class="fas fa-plus"></i>
  </div>
</a>
</body>
</html>