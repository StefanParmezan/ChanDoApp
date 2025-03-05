async function loadHabits() {
    try {
        const response = await fetch("/api/habits");
        const habits = await response.json();

        const habitList = document.querySelector(".container");
        habitList.innerHTML = ""; // Очищаем контейнер перед добавлением новых привычек

        habits.forEach(habit => {
            const habitCard = document.createElement("a");
            habitCard.href = `page1.html`; // Замените на нужный URL
            habitCard.className = "card cardd";

            habitCard.innerHTML = `
                <div class="progress-circle" style="background-color: ${habit.color};">
                    <img src="./imgs/BlueBrain.png" alt="Brain Icon" class="brain-icon" />
                </div>
                <div class="details">
                    <h3>${habit.title}</h3>
                    <div class="stats">
                        <div>
                            <span class="ppoo">Старт</span>
                            <span style="color: #00aaff">${habit.startDate}</span>
                        </div>
                        <div>
                            <span class="pop">Стрик</span>
                            <span class="pdpd" style="color: #00aaff">1</span>
                        </div>
                        <div>
                            <span>Категория</span>
                            <span style="color: #00aaff">${habit.category}</span>
                        </div>
                    </div>
                </div>
            `;

            habitList.appendChild(habitCard);
        });
    } catch (error) {
        console.error("Ошибка при загрузке привычек:", error);
    }
}

// Загружаем привычки при загрузке страницы
window.onload = loadHabits;