const user = document.getElementById("user");
const localStorage = window.localStorage;
const engineer = JSON.parse(localStorage.getItem("key"));

document.addEventListener("DOMContentLoaded",loadUser);

function loadUser() {
    console.log(engineer.name)
    user.innerHTML = engineer.name;
}