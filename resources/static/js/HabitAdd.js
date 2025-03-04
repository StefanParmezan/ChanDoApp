function changeTextColor(color, element, imageUrl) {
  document.getElementById("text-to-change").style.color = color;
  document.getElementById("day-input").style.color = color;
  document.getElementById("month-input").style.color = color;
  document.getElementById("year-input").style.color = color;
  document.getElementById("icon-image").src = imageUrl;

  // Remove check mark from all color options
  const colorOptions = document.querySelectorAll(".color-option");
  colorOptions.forEach((option) => {
    option.innerHTML = "";
  });

  // Add check mark to the selected color option
  element.innerHTML = '<i class="fas fa-check text-black"></i>';
}

function navigateToPage() {
  const yearInput = document.getElementById("year-input").value;
  const currentYear = new Date().getFullYear();

  if (isNaN(yearInput) || yearInput < currentYear) {
    document.getElementById("error-message").innerText =
      "Некорректный год. Пожалуйста, введите правильный год.";
  } else {
    document.getElementById("error-message").innerText = "";
    window.location.href = "https://example.com"; // Replace with your desired URL
  }
}

function goBack() {
  window.location.href = "/habitlist"; // Replace with your desired URL
}

function setCurrentDate() {
  const now = new Date();
  const day = String(now.getDate()).padStart(2, "0");
  const month = String(now.getMonth() + 1).padStart(2, "0"); // Months are zero-based
  const year = now.getFullYear();
  document.getElementById("day-input").value = day;
  document.getElementById("month-input").value = month;
  document.getElementById("year-input").value = year;
}

window.onload = setCurrentDate;



