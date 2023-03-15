$('#delete').on('show.bs.modal', async ev => {
    let button = $(ev.relatedTarget);
    let id = button.data('id');
    await showDeleteModal(id);
})

async function showDeleteModal(id) {
    let user = await getUser(id);
    let form = document.forms["formDeleteUser"];
    form.id.value = user.id;
    form.firstName.value = user.name;
    form.lastName.value = user.surname;
    form.age.value = user.age;
    form.email.value = user.email;


    $('#rolesDeleteUser').empty();

    await fetch("http://localhost:8080/admin/api/roles")
        .then(res => res.json())
        .then(roles => {
            roles.forEach(role => {
                let el = document.createElement("option");
                el.text = role.role;
                $('#rolesDeleteUser')[0].appendChild(el);
            })
        });
}

async function getUser(id) {
    let url = "http://localhost:8080/admin/api/users/" + id;
    let response = await fetch(url);
    return await response.json();
}
