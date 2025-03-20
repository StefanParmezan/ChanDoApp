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
window.onload = function () {
    setCurrentDate();

    // Базовая валидация ввода
    document.querySelectorAll('.date-group input').forEach(input => {
        input.addEventListener('input', function (e) {
            // Оставляем только цифры
            this.value = this.value.replace(/\D/g, '');
        });
    });
};

// Валидация перед отправкой
document.querySelector('form').addEventListener('submit', function (e) {
    const day = document.getElementById('day-input').value;
    const month = document.getElementById('month-input').value;
    const year = document.getElementById('year-input').value;

    if (!isValidDate(day, month, year)) {
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

function validateAndSubmit() {
    const day = document.getElementById('day-input').value.padStart(2, '0');
    const month = document.getElementById('month-input').value.padStart(2, '0');
    const year = document.getElementById('year-input').value;

    // Установка значения в скрытое поле даты
    const formattedDate = `${year}-${month}-${day}`;
    document.getElementById('start-date').value = formattedDate;

    // Валидация даты
    const date = new Date(formattedDate);
    if (isNaN(date.getTime())) {
        alert('Некорректная дата! Проверьте введенные значения');
        return false;
    }

    return true;
}

// Инициализация полей текущей датой
document.addEventListener('DOMContentLoaded', function () {
    const today = new Date();
    document.getElementById('day-input').value = today.getDate();
    document.getElementById('month-input').value = today.getMonth() + 1;
    document.getElementById('year-input').value = today.getFullYear();
});


document.addEventListener('DOMContentLoaded', function () {
    const today = new Date();

    // Устанавливаем текущую дату
    document.getElementById('day-input').value = today.getDate().toString().padStart(2, '0');
    document.getElementById('month-input').value = (today.getMonth() + 1).toString().padStart(2, '0');
    document.getElementById('year-input').value = today.getFullYear();
});

document.addEventListener('DOMContentLoaded', () => {
    // Находим элементы
    const titleInput = document.getElementById('text-to-change');
    const errorMessage = document.getElementById('error-display');

    // Если есть сообщение об ошибке
    if (errorMessage) {
        // Добавляем обработчик события 'focus' на поле ввода
        titleInput.addEventListener('focus', () => {
            errorMessage.style.display = 'none'; // Скрываем сообщение об ошибке
        });
    }
});

// Функция для отображения ошибки
function showError(message) {
    const buttonGroup = document.querySelector('.button-group');
    const errorBlock = document.querySelector('.error-message');

    // Если блок ошибки уже существует, обновляем его текст
    if (errorBlock) {
        errorBlock.textContent = message;
    } else {
        // Создаем новый блок ошибки
        const newErrorBlock = document.createElement('p');
        newErrorBlock.className = 'error-message';
        newErrorBlock.style.color = 'red';
        newErrorBlock.textContent = message;

        // Заменяем кнопку на сообщение об ошибке
        buttonGroup.innerHTML = ''; // Очищаем содержимое кнопки
        buttonGroup.appendChild(newErrorBlock);
    }
}

// Функция для скрытия ошибки при взаимодействии с полями
function hideErrorOnInteraction() {
    const inputs = document.querySelectorAll('input, select');
    const buttonGroup = document.querySelector('.button-group');

    inputs.forEach(input => {
        input.addEventListener('focus', () => {
            // Удаляем блок ошибки, если он существует
            const errorBlock = document.querySelector('.error-message');
            if (errorBlock) {
                buttonGroup.removeChild(errorBlock);

                // Восстанавливаем кнопку "Начать"
                const submitButton = document.createElement('button');
                submitButton.type = 'submit';
                submitButton.textContent = 'Начать';
                buttonGroup.appendChild(submitButton);
            }
        });
    });
}

// Инициализация событий
document.addEventListener('DOMContentLoaded', () => {
    hideErrorOnInteraction();
});
// Функция для валидации и отправки формы

// Функция для отображения ошибки в модальном окне

// Функция для закрытия модального окна
function closeModal() {
    const modal = document.getElementById('error-modal');
    modal.style.display = 'none';
}

// Функция для изменения цвета текста и иконки


// Инициализация событий
document.addEventListener('DOMContentLoaded', () => {
    // Добавляем обработчики событий для скрытия ошибки при взаимодействии с полями
    const inputs = document.querySelectorAll('input, select');
    inputs.forEach(input => {
        input.addEventListener('focus', closeModal);
    });
});