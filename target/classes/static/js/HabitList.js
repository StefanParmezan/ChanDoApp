document.addEventListener("DOMContentLoaded", function() {
    // Сопоставление имен файлов с цветами
    const colorMap = {
        'BlueBrain.png': '#00aaff',   // Синий
        'PurpleBrain.png': '#A340FD', // Розовый
        'GreenBrain.png': '#59F585',  // Зеленый
        'LightBlueBrain.png': '#30E4F2',
        'OrangeBrain.png': '#F08A3D',
        'RedBrain.png': '#EF6475',
        'YellowBrain.png': '#F5D312',
        'PinkBrain.png': '#EC4899' // Добавляем цвет для PinkBrain
    };

    // Получаем все карточки с привычками
    const cards = document.querySelectorAll('.card');

    cards.forEach(card => {
        // Получаем элемент иконки мозга внутри текущей карточки
        const brainIcon = card.querySelector('.brain-icon');

        // Получаем путь к иконке
        const iconSrc = brainIcon.getAttribute('src');

        // Извлекаем имя файла (убираем параметры, если они есть)
        const iconFileName = iconSrc.split('/').pop().split('?')[0]; // Убираем параметры после "?"

        // Получаем цвет из colorMap на основе имени файла
        const iconColor = colorMap[iconFileName] || ''; // По умолчанию черный цвет

        // Логирование для отладки
        console.log('Иконка:', iconFileName, 'Цвет:', iconColor);

        // Применяем цвет к тексту внутри карточки
        const textElements = card.querySelectorAll('.details h3, .details .stats div span:last-child');
        textElements.forEach(textElement => {
            textElement.style.color = iconColor;
        });
    });
});

document.addEventListener('DOMContentLoaded', function () {
    const searchInput = document.getElementById('search-input');
    const habitCards = document.querySelectorAll('.card');

    searchInput.addEventListener('input', function () {
        const searchText = this.value.trim().toLowerCase();

        habitCards.forEach(card => {
            const habitTitle = card.querySelector('.details h3').textContent.toLowerCase();
            if (habitTitle.includes(searchText)) {
                card.style.display = 'flex'; // Показываем карточку
            } else {
                card.style.display = 'none'; // Скрываем карточку
            }
        });
    });
});