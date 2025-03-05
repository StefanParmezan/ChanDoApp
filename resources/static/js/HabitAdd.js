// Функция для изменения цвета текста и иконки
function changeTextColor(color, element, imageUrl) {
  document.getElementById("text-to-change").style.color = color;
  document.getElementById("day-input").style.color = color;
  document.getElementById("month-input").style.color = color;
  document.getElementById("year-input").style.color = color;
  document.getElementById("icon-image").src = imageUrl;

  // Убираем галочку у всех цветов
  const colorOptions = document.querySelectorAll(".color-option");
  colorOptions.forEach((option) => {
    option.innerHTML = "";
  });

  // Добавляем галочку к выбранному цвету
  element.innerHTML = '<i class="fas fa-check text-black"></i>';
}

// Функция для проверки года и перенаправления
function navigateToPage() {
  const yearInput = document.getElementById("year-input").value;
  const currentYear = new Date().getFullYear();

  if (isNaN(yearInput) || yearInput < currentYear) {
    document.getElementById("error-message").innerText =
        "Некорректный год. Пожалуйста, введите правильный год.";
  } else {
    document.getElementById("error-message").innerText = "";
    addHabit(); // Вызываем функцию добавления привычки
  }
}

// Функция для возврата на страницу списка привычек
function goBack() {
  window.location.href = "/habitlist"; // Замените на нужный URL
}

// Функция для установки текущей даты по умолчанию
function setCurrentDate() {
  const now = new Date();
  const day = String(now.getDate()).padStart(2, "0");
  const month = String(now.getMonth() + 1).padStart(2, "0"); // Месяцы начинаются с 0
  const year = now.getFullYear();
  document.getElementById("day-input").value = day;
  document.getElementById("month-input").value = month;
  document.getElementById("year-input").value = year;
}

// Функция для добавления привычки
async function addHabit() {
  const title = document.getElementById("text-to-change").value;
  const day = document.getElementById("day-input").value;
  const month = document.getElementById("month-input").value;
  const year = document.getElementById("year-input").value;
  const category = document.getElementById("exclude-options").value;
  const color = document.querySelector(".color-option.selected")?.style.backgroundColor || "#3b82f6";

  const startDate = `${year}-${month.padStart(2, "0")}-${day.padStart(2, "0")}`;

  const habit = {
    title,
    startDate,
    color,
    category,
    completed: false, // По умолчанию привычка не выполнена
  };

  try {
    const response = await fetch("/api/habits", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(habit),
    });

    if (response.ok) {
      alert("Привычка успешно добавлена!");
      window.location.href = "/habitlist"; // Перенаправление на страницу списка привычек
    } else {
      alert("Ошибка при добавлении привычки");
    }
  } catch (error) {
    console.error("Ошибка:", error);
  }
}

// Устанавливаем текущую дату при загрузке страницы
window.onload = setCurrentDate;

// Привязываем функцию navigateToPage к кнопке "Начать"
document.querySelector(".button-group button").addEventListener("click", navigateToPage);