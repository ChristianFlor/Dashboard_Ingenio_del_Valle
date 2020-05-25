const user = document.getElementById("user");
const localStorage = window.localStorage;
const key = JSON.parse(localStorage.getItem("key"));

document.addEventListener("DOMContentLoaded",loadUser);

function loadUser() {
    console.log(key.name);
    user.innerHTML = key.name;
}