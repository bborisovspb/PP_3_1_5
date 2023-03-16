$(
    async function () {
    await thisUser();
}
);



async function thisUser() {
    fetch("http://localhost:8080/userShow")
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



