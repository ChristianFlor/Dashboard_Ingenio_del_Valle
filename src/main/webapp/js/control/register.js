const firstName = document.getElementById("firstName");
const lastName = document.getElementById("lastName");
const inputID = document.getElementById("inputID");
const inputUsernameRegister = document.getElementById("inputUsernameRegister");
const inputEmail = document.getElementById("inputEmail");
const inputPassword = document.getElementById("inputPassword");
const repeatPassword = document.getElementById("repeatPassword");
const registerBTN = document.getElementById("registerBTN");

const  localStorage = window.localStorage;

registerBTN.addEventListener("click",register);

function register() {
    var id = inputID.value;
    console.log(id);
    var name = firstName.value;
    console.log(name);
    var lastname = lastName.value;
    console.log(lastname);
    var username = inputUsernameRegister.value;
    console.log(username);
    var email = inputEmail.value;
    console.log(email);
    /*if(inputPassword.value == repeatPassword.value){
        var passaword = inputPassword;
    }
    else{
        var nScript = document.createElement("script");
        nScript.appendChild(alert("La contraseñas no coinciden"));
    }*/
    var password = inputPassword.value;
    console.log(password);

    var engineer = new Engineer(name,lastname,id,username,password,email);

    var json = engineer.toJson();
    console.log(json);

    var xhr = new XMLHttpRequest();
    xhr.onloadend = function(){
        console.log(json);
    };

    xhr.open("POST","api/engineer/register");
    xhr.setRequestHeader("Content-Type","application/json");
    xhr.send(json);
}

