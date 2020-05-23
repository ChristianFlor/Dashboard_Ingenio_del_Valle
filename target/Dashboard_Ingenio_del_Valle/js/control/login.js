const inputUsernameLogin = document.getElementById("inputUsernameLogin");
const inputPassword = document.getElementById("inputPassword");
const loginBTN = document.getElementById("loginBTN");
const  localStorage = window.localStorage;

loginBTN.addEventListener("click",login);

function login( e ) {
    e.preventDefault();
    var username = inputUsernameLogin.value;
    console.log(username);
    var password = inputPassword.value;
    console.log(password);

    console.log(username+" "+password);

    var xhr = new XMLHttpRequest();
    xhr.onloadend = function(){
        console.log(xhr.responseText);
        var key= (JSON.parse(xhr.responseText));
        if(key.id === undefined){
            alert("contraseña errada");
        }else{
            localStorage.setItem("key",xhr.responseText);
            window.location.href= "index.html";
        }

    };

    xhr.open("GET","api/engineer/login/"+username+"/"+password);
    xhr.send();
}

