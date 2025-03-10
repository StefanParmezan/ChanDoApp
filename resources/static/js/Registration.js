function hideMessage() {
    const messageContainer = document.getElementById('messageContainer');
    if (messageContainer) {
        messageContainer.style.visibility = 'hidden'; // Скрываем сообщение, но сохраняем место
    }
}

// Функция для отображения сообщения
function showMessage() {
    const messageContainer = document.getElementById('messageContainer');
    if (messageContainer) {
        messageContainer.style.visibility = 'visible'; // Показываем сообщение
    }
}

// Функция для переключения видимости пароля
function togglePasswordVisibility(inputId) {
    const passwordInput = document.getElementById(inputId);
    if (passwordInput.type === 'password') {
        passwordInput.type = 'text';
    } else {
        passwordInput.type = 'password';
    }
}

// Функция для очистки формы
function clearForm() {
    const form = document.querySelector('form');
    if (form) {
        form.reset(); // Сбрасываем значения всех полей формы
    }
}

// Добавляем обработчик события на загрузку страницы
window.onload = function() {
    const inputs = document.querySelectorAll('input');
    inputs.forEach(input => {
        input.addEventListener('focus', hideMessage);
    });

    // Показываем сообщение, если оно есть
    const successMessage = document.getElementById('successMessage');
    const errorMessage = document.getElementById('errorMessage');
    if (successMessage || errorMessage) {
        showMessage(); // Показываем сообщение, если оно есть
    }

    // Очищаем форму, если в URL есть параметр success
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has('success')) {
        clearForm(); // Очищаем форму
    }
};