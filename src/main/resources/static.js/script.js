
function delPrefix() {
    const roleCells = document.querySelectorAll(".table tbody td:nth-child(6)"); // Выбираем все ячейки с ролями

    roleCells.forEach(function (cell) {
        let roles = cell.textContent.trim().split(","); // Получаем список ролей из содержимого ячейки
        let cleanedRoles = roles.map(function (role) {
            return role.replace("ROLE_", ""); // Удаляем префикс "ROLE_" из каждой роли
        });
        cell.textContent = cleanedRoles.join(" "); // Заменяем содержимое ячейки на очищенные роли
    });
}


// Вызов функции для получения пользователей при загрузке страницы
document.addEventListener('DOMContentLoaded', function() {
    delPrefix();
});

document.addEventListener("DOMContentLoaded", function () {
    const roleCells = document.querySelectorAll(".table tbody td:nth-child(6)"); // Выбираем все ячейки с ролями

    roleCells.forEach(function (cell) {
        let roles = cell.textContent.trim().split(","); // Получаем список ролей из содержимого ячейки
        let cleanedRoles = roles.map(function (role) {
            return role.replace("ROLE_", ""); // Удаляем префикс "ROLE_" из каждой роли
        });
        cell.textContent = cleanedRoles.join(", "); // Заменяем содержимое ячейки на очищенные роли
    });
});

