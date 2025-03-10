// Установка текущей даты
function setCurrentDate() {
    const now = new Date();
    const day = String(now.getDate()).padStart(2, "0");
    const month = String(now.getMonth() + 1).padStart(2, "0");
    const year = now.getFullYear();

    document.getElementById("day-input").value = day;
    document.getElementById("month-input").value = month;
    document.getElementById("year-input").value = year;
}

// Изменение цвета текста и даты
function changeTextColor(color, element, imageUrl, colorName) {
    // Меняем цвет основного текста
    const textInput = document.getElementById("text-to-change");
    textInput.style.color = color;

    // Меняем цвет даты
    const dateInputs = document.querySelectorAll('.date-group input');
    dateInputs.forEach(input => input.style.color = color);

    // Обновляем иконку
    document.getElementById("icon-image").src = imageUrl;

    // Обновляем галочки
    document.querySelectorAll(".color-option").forEach(opt => opt.innerHTML = "");
    element.innerHTML = '<i class="fas fa-check text-black"></i>';

    // Сохраняем выбранный цвет
    document.getElementById("selected-color").value = colorName;
}

// Навигация назад
function goBack() {
    window.location.href = "/habitlist";
}

// Инициализация при загрузке
window.onload = function() {
    setCurrentDate();

    // Базовая валидация ввода
    document.querySelectorAll('.date-group input').forEach(input => {
        input.addEventListener('input', function(e) {
            // Оставляем только цифры
            this.value = this.value.replace(/\D/g, '');
        });
    });
};

// Валидация перед отправкой
document.querySelector('form').addEventListener('submit', function(e) {
    const day = document.getElementById('day-input').value;
    const month = document.getElementById('month-input').value;
    const year = document.getElementById('year-input').value;

    if(!isValidDate(day, month, year)) {
        e.preventDefault();
        alert('Пожалуйста, введите корректную дату (ДД.ММ.ГГГГ)');
    }
});

// Проверка корректности даты
function isValidDate(day, month, year) {
    const date = new Date(`${year}-${month}-${day}`);
    return date &&
        date.getDate() == day &&
        date.getMonth() + 1 == month &&
        date.getFullYear() == year;
}