const user = document.getElementById("user");
const firstName = document.getElementById("firstName");
const lastName = document.getElementById("lastName");
const inputID = document.getElementById("inputID");
const inputUsernameRegister = document.getElementById("inputUsernameRegister");
const inputEmail = document.getElementById("inputEmail");
const saveChanges = document.getElementById("saveChanges");

const localStorage = window.localStorage;
const engineer = JSON.parse(localStorage.getItem("key"));

const sectorsHRef="sectors.html";
const sectorI="fas fa-fw fa-tachometer-alt";
const sectorName = "Sectors";

var pass = engineer.password;
const asign_sectorsHRef="assign-sector.html";
const assing_sectorI="fas fa-fw fa-user";
const assign_sectorName="Assign Sector";
document.addEventListener("DOMContentLoaded",loadUser);

function loadUser() {

    console.log(engineer.name);
    user.innerHTML = engineer.name;
    if(engineer.username === "admin" && engineer.password === "admin"){
        loadPagesUser(asign_sectorsHRef,assing_sectorI,assign_sectorName);
    }else{
        loadPagesUser(sectorsHRef,sectorI,sectorName);
    }
}

function loadPagesUser(hrefS, iClassName, name) {
    pages.innerHTML="";
    var a= document.createElement("a");
    a.className="nav-link";
    a.href=hrefS;
    var i= document.createElement("i");
    i.className=iClassName;
    var span= document.createElement("span");
    span.innerHTML=name;
    a.append(i,span);
    pages.appendChild(a);

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