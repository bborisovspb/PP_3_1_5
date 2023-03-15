$(
    async function () {
    await thisUser();
}
);

// $() сокращение функции getElementById() которая возвращает id DOM дерева HTML страницы

// async всегда возвращает промис
// await это кусок async'а, означает что мы ждем выполнения кода справа от await

// Промис - средство организации асинхронного кода

// Создающий код - певец
// Потребляющий код - фанаты
// Промис - список для подписки(то, что соединяет создающий и потребляющий код)
// Промис дает возможность потребляющему коду получать результат работы создающего
// Промис делает результат доступным для кода, который подписан на него, когда результат готов
//

async function thisUser() {
    fetch("http://localhost:8080/admin/user")
        .then(res => res.json())
        .then(data => {
            // Добавляем информацию в шапку
            $('#headerUsername').append(data.name);

            let roles = data.roles.map(role => " " + role.role.substring(5));
                // data.role
            $('#headerRoles').append(roles);

            //Добавляем информацию в таблицу
            let user = `$(
            <tr>
                <td>${data.id}</td>
                <td>${data.name}</td>
                <td>${data.surname}</td>
                <td>${data.age}</td>
                <td>${data.email}</td>
                <td>${roles}</td>
                )`;
            $('#userPanelBody').append(user);
        })
}



