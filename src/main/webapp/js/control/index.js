const user = document.getElementById("user");
const pages = document.getElementById("pages");
const localStorage = window.localStorage;
const key = JSON.parse(localStorage.getItem("key"));

const sectorsHRef="sectors.html";
const sectorI="fas fa-fw fa-tachometer-alt";
const sectorName = "Sectors";


const asign_sectorsHRef="assign-sector.html";
const assing_sectorI="fas fa-fw fa-user";
const assign_sectorName="Assign Sector";
const registerHRef="register.html";
const registerI="fas fa-fw fa-user";
const registerName="Register";

document.addEventListener("DOMContentLoaded",init);
function init(){
    loadUser();
}

function loadUser() {
    console.log(key.name);
    user.innerHTML = key.name;
    if(key.username === "admin" && key.password === "admin"){
        loadPagesUser2(asign_sectorsHRef,assing_sectorI,assign_sectorName,registerHRef,registerI,registerName);
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
}
function loadPagesUser2(hrefS, iClassName, name, hrefS1, iClassName1, name1) {
    pages.innerHTML="";
    var a= document.createElement("a");
    a.className="nav-link";
    a.href=hrefS;
    var i= document.createElement("i");
    i.className=iClassName;
    var span= document.createElement("span");
    span.innerHTML=name;
    a.append(i,span);

    var a1= document.createElement("a");
    a1.className="nav-link";
    a1.href=hrefS1;
    var i1= document.createElement("i");
    i1.className=iClassName1;
    var span1= document.createElement("span");
    span1.innerHTML=name1;
    a1.append(i1,span1);
    pages.appendChild(a);
    pages.appendChild(a1);
}

