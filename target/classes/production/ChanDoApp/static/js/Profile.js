// Ожидаем полной загрузки DOM
document.addEventListener('DOMContentLoaded', () => {
    // Получаем необходимые элементы
    const profileIcon = document.getElementById('profile-icon');
    const avatarModal = document.getElementById('avatar-modal');
    const avatarsGrid = document.getElementById('avatars-grid');

    // Массив путей к аватаркам
    const avatarImages = [
        'imgs/avatar1.png',
        'imgs/avatar2.png',
        'imgs/avatar3.png',
        'imgs/avatar4.png',
        'imgs/avatar5.png',
        'imgs/avatar6.png'
    ];

    // Создаем аватарки
    function createAvatars() {
        for (let i = 0; i < 6; i++) {
            const avatar = document.createElement('img');
            avatar.className = 'avatar';
            avatar.src = avatarImages[i];
            avatar.alt = `Avatar ${i + 1}`;
            avatar.onclick = () => selectAvatar(avatar, i);
            avatarsGrid.appendChild(avatar);
        }
    }

    // Функция выбора аватарки
    function selectAvatar(element, index) {
        profileIcon.innerHTML = `<img src="${avatarImages[index]}" alt="Selected Avatar" style="width: 100%; height: 100%; border-radius: 50%;">`;
        closeModal();
    }

    // Открытие модального окна при клике на профильное изображение
    profileIcon.addEventListener('click', () => {
        avatarModal.classList.add('active');
    });

    // Закрытие модального окна
    function closeModal() {
        avatarModal.classList.remove('active');
    }

    // Создание аватарок при загрузке страницы
    createAvatars();

    // Обработчик для выпадающего меню
    const menuToggle = document.getElementById('menu-toggle');
    const dropdownMenu = document.getElementById('dropdown-menu');
    let isMenuOpen = false;

    menuToggle.addEventListener('click', (e) => {
        e.stopPropagation();
        isMenuOpen = !isMenuOpen;
        dropdownMenu.classList.toggle('active');
    });

    // Закрытие меню при клике вне его
    document.addEventListener('click', (e) => {
        if (isMenuOpen && !dropdownMenu.contains(e.target)) {
            dropdownMenu.classList.remove('active');
            isMenuOpen = false;
        }
    });
});