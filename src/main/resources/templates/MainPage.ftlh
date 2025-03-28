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
        <a href="/profile" class="menu-item profile">
          <img
                  alt="Profile picture"
                  height="40"
                  src="./imgs/${Avatar}.png"
                  width="40"
          />
          <span class="profile">Профиль</span>
        </a>
        <div class="menu">
          <a href="/premium" class="menu-item premium">
            <i class="fas fa-star"></i>
            <span>Премиум</span>
          </a>
          <a href="/habitlist" class="menu-item">
            <i class="fas fa-home"></i>
            <span>Ваши привычки</span>
          </a>
          <a href="/shop" class="menu-item">
            <i class="fas fa-search"></i>
            <span>Магазин</span>
          </a>
          <a href="/support" class="menu-item">
            <i class="fas fa-question-circle"></i>
            <span>Поддержка</span>
          </a>
          <a href="/login" class="menu-item">
            <i class="fas fa-sign-out-alt"></i>
            <span>Выход</span>
          </a>
        </div>
    </div>
    </div>
  </body>
</html>
