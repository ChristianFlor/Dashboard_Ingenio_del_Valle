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

document.addEventListener("DOMContentLoaded",init);
function init(){
    loadUser();
}

function loadUser() {
    console.log(key.name);
    user.innerHTML = key.name;
    if(key.username === "admin" && key.password === "admin"){
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
}
