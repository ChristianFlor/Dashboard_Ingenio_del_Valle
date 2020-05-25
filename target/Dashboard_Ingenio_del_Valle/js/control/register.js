const firstName = document.getElementById("firstName");
const lastName = document.getElementById("lastName");
const inputID = document.getElementById("inputID");
const inputUsernameRegister = document.getElementById("inputUsernameRegister");
const inputEmail = document.getElementById("inputEmail");
const inputPassword = document.getElementById("inputPassword");
const registerBTN = document.getElementById("registerBTN");

const  localStorage = window.localStorage;

registerBTN.addEventListener("click",register);

function register() {
    var id = inputID.value;
    var name = firstName.value;
    var lastname = lastName.value;
    var username = inputUsernameRegister.value;
    var email = inputEmail.value;
    var password = inputPassword.value;

    var engineer = new Engineer(name,lastname,id,username,password,email);

    var json = engineer.toJson();
   
    var xhr = new XMLHttpRequest();
    xhr.onloadend = function(){
        console.log(json);
    };

    xhr.open("POST","api/engineer/register");
    xhr.setRequestHeader("Content-Type","application/json");
    xhr.send(json);
}

