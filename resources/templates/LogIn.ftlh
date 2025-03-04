<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Вход</title>
    <link rel="stylesheet" href="./css/Registration.css"> <!-- Используйте тот же файл стилей -->
    <style>
        .error-message {
            color: red; /* Красный цвет для сообщения об ошибке */
            margin-top: 10px;
        }
        .success-message {
            color: green; /* Зеленый цвет для успешного сообщения */
            margin-top: 10px;
        }
        .message-container {
            margin-bottom: 20px; /* Отступ снизу для сообщений */
            padding: 10px; /* Отступ внутри контейнера */
            border-radius: 5px; /* Закругленные углы */
        }
    </style>
</head>
<body>
<div class="container">
    <img src="./imgs/Brain-icon.png" alt="Brain icon"/>
    <h1>Вход</h1>
    <form action="/login" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> <!-- CSRF-токен -->
        <input type="text" name="username" placeholder="Имя пользователя" value="${username!''}" required/>
        <input type="password" name="password" placeholder="Пароль" value="${password!''}" required/>
        <button type="submit">Войти</button>
        <#if RequestParameters.error??>
            <div class="error-message">Неверный логин или пароль.</div>
        </#if>
        <#if RequestParameters.expired??>
            <div class="alert alert-warning">
                Ваша сессия истекла. Пожалуйста, войдите снова.
            </div>
        </#if>
    </form>

    <p>Нет аккаунта? <a href="/registration" class="Log-in-button">Регистрация</a></p>
</div>
</body>
</html>