<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Вход</title>
    <link rel="stylesheet" href="./css/MainPage.css">
    <title>ChanDo Menu</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
      rel="stylesheet"
    />
    <link
      href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&amp;display=swap"
      rel="stylesheet"
    />
  </head>
  <body>
    <div class="container">
      <div class="logo">
        <img
          alt="Chando logo"
          height="80"
          src="./imgs/ChanDo_logo.png"
          width="80"
        />
      </div>
      <div class="menu">
        <button class="menu-item profile">
          <a href="/profile">
            <img
              alt="Profile picture"
              height="40"
              src="./imgs/ProfileSt.png"
              width="40"
            />
            <span class="profille">Профиль</span>
          </a>
        </button>
        <button class="menu-item premium">
          <a href="/premium">
            <i class="fas fa-star"></i>
            <span>Премиум</span>
          </a>
        </button>
        <button class="menu-item">
          <a href="/habitlist">
            <i class="fas fa-home"></i>
            <span>Ваши привычки</span>
          </a>
        </button>
        <button class="menu-item">
          <a href="/achievements">
            <i class="fas fa-trophy"></i>
            <span>Достижения</span>
          </a>
        </button>
        <button class="menu-item">
          <a href="/support">
            <i class="fas fa-question-circle"></i>
            <span>Поддержка</span>
          </a>
        </button>
        <button class="menu-item">
          <a href="/login">
            <i class="fas fa-sign-out-alt"></i>
            <span>Выход</span>
          </a>
        </button>
      </div>
    </div>
  </body>
</html>
