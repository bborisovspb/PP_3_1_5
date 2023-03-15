$(async function () {
   await editUser();

});

async function editUser() {

    const editForm = document.forms["formEditUser"];

    editForm.addEventListener("submit", ev => {
        ev.preventDefault();
        let editUserRoles = [];
        for (let i = 0; i < editForm.roles.options.length; i++) {
            if (editForm.roles.options[i].selected)
                editUserRoles.push({
                role: editForm.roles.options[i].value
            })
        }


        fetch("http://localhost:8080/admin/api/users", {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: editForm.id.value,
                name: editForm.firstName.value,
                username: editForm.userName.value,
                surname: editForm.lastName.value,
                age: editForm.age.value,
                email: editForm.email.value,
                password: editForm.password.value,
                roles: editUserRoles
            })
        }).then(() => {
            editForm.reset();
            allUsers();
            $('#editFormCloseButton').click();
        })
    })
}