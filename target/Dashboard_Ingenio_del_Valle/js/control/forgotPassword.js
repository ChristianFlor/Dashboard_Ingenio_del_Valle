const exampleInputEmail = document.getElementById("exampleInputEmail");
const resetPassword = document.getElementById("resetPassword");

resetPassword.addEventListener("click",getEngineer);

function getEngineer() {
    var email = exampleInputEmail.value;
    console.log(email);
    var xhr = new XMLHttpRequest();
    xhr.onloadend = function () {
        console.log("OK")
    };
    xhr.open("HEAD","api/engineer/enviaremail/"+email);
    xhr.send();
}

