<!DOCTYPE html>
<html lang="en">
<head>
    <script src="/js/Registration.js" type="text/javascript"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация</title>
    <link rel="stylesheet" href="./css/Registration.css">
    <style>
        .message-container {
            margin-bottom: 20px; /* Отступ снизу для сообщений */
            padding: 10px; /* Отступ внутри контейнера */
            border-radius: 5px; /* Закругленные углы */
            visibility: hidden; /* Скрыто по умолчанию, но занимает место */
        }
        .error-message {
            color: red; /* Красный цвет для сообщения об ошибке */
            font-weight: bold; /* Жирный текст */
            background-color: #181616; /* Фон для сообщения об ошибке */
            border: 1px solid #181616; /* Рамка для сообщения об ошибке */
        }
        .success-message {
            color: green; /* Зеленый цвет для успешного сообщения */
            font-weight: bold;
            background-color: #181616; /* Фон для успешного сообщения */
            border: 1px solid #181616; /* Рамка для успешного сообщения */
        }
        .password-container {
            position: relative; /* Для позиционирования иконки */
        }
        .toggle-password {
            position: absolute; /* Абсолютное позиционирование */
            right: 20px; /* Отступ от правого края *//
            top: 50%; /* Центрирование по вертикали */
            transform: translateY(170%); /* Центрирование по вертикали */
            cursor: pointer; /* Указатель при наведении на кнопку */
        }
        .modal-content img {
            width: 150px; /* Ширина изображения галочки */
            height: 150px; /* Высота изображения галочки */
            margin-bottom: 10px; /* Отступ снизу */
        }
        .close {
            color: grey; /* Цвет кнопки закрытия */
            float: right; /* Справа */
            font-size: 28px; /* Размер шрифта */
            font-weight: bold; /* Жирный текст */
        }
        .close:hover,
        .close:focus {
            color: black; /* Цвет при наведении */
            text-decoration: none; /* Без подчеркивания */
            cursor: pointer; /* Указатель при наведении */
        }
    </style>

</head>
<body>
<div class="container">
    <img src="./imgs/Brain-icon.png" alt="Brain icon"/>
    <h1>Регистрация</h1>
    <form action="/registration" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="text" name="username" placeholder="Имя" value="${username!''}" required/>
        <input type="text" name="email" placeholder="Ваш email" value="${email!''}" required/>
        <input type="number" name="date" placeholder="Возраст (0-100)" min="0" max="100" value="${date!''}" required/>
        <div class="password-container">
            <input type="password" id="password" name="password" placeholder="Пароль (A-Z), 8 символов, (!-*)" maxlength="30" required/>
            <img src="./imgs/eye-icon.gif" class="toggle-password" onclick="togglePasswordVisibility('password')" style="width: 33px; height: 19px;" alt="eye icon"/>
        </div>
        <div class="password-container">
            <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Повторите пароль" maxlength="30" required/>
        </div>

        <button type="submit">Создать аккаунт</button>
    </form>

    <p>Есть аккаунт? <a href="/login" class="Log-in-button">Войти</a></p>

    <div id="messageContainer" class="message-container">
        <#if successMessage??>
            <div id="successMessage" class="success-message">
                <p>${successMessage}</p>
            </div>
        </#if>
        <#if errorMessage??>
            <div id="errorMessage" class="error-message">
                <p>${errorMessage}</p>
            </div>
        </#if>
    </div>
</div>

</body>
</html>