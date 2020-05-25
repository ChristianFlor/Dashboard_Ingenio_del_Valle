const user = document.getElementById("user");
const firstName = document.getElementById("firstName");
const lastName = document.getElementById("lastName");
const inputID = document.getElementById("inputID");
const inputUsernameRegister = document.getElementById("inputUsernameRegister");
const inputEmail = document.getElementById("inputEmail");
const saveChanges = document.getElementById("saveChanges");

const localStorage = window.localStorage;
const engineer = JSON.parse(localStorage.getItem("key"));
var pass = engineer.password;

document.addEventListener("DOMContentLoaded",loadUser);

function loadUser() {
    user.innerHTML = engineer.name;
    firstName.value = engineer.name;
    lastName.value = engineer.lastname;
    inputID.value = engineer.id;
    inputUsernameRegister.value = engineer.username;
    inputEmail.value = engineer.email;
}

saveChanges.addEventListener("click",save);

function save() {
    var id = inputID.value;
    var name = firstName.value;
    var lastname = lastName.value;
    var username = inputUsernameRegister.value;
    var email = inputEmail.value;
    var password = pass;

    var engineer = new Engineer(name,lastname,id,username,password,email);

    var json = engineer.toJson();

    var xhr = new XMLHttpRequest();
    xhr.onloadend = function(){
        console.log(json);
    };

    xhr.open("PUT","api/engineer/update");
    xhr.setRequestHeader("Content-Type","application/json");
    xhr.send(json);
}