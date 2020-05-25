const tableEngineer = document.getElementById("tableEngineer");
const selectEngineer = document.getElementById("selectEngineer");
const pages = document.getElementById("pages");
const selected = tableEngineer.getElementsByClassName('selected');
const sectoresB = document.getElementById("sectoresB");
const assign = document.getElementById("assign");
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

var idEngineer;
var sectores = [];

document.addEventListener("DOMContentLoaded",init);

function init(){
    loadTableEngineer();
    loadSectores();
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
function loadTableEngineer() {
    tableEngineer.innerHTML = "";
    var xhr = new XMLHttpRequest();
    var tr = document.createElement("tr");
    var th= document.createElement("th");
    th.innerHTML= "Listado de ingenieros";
    th.className="text-center";
    th.scope="col";
    th.colSpan=6;
    tr.append(th);
    tableEngineer.appendChild(tr);
    xhr.onloadend = function () {
        var objects = JSON.parse(xhr.responseText);
        for(let i = 0; i<objects.length; i++){
            let nEngineer = objects[i];
            loadEngineer(nEngineer);

        }
    };
    xhr.open("GET","api/engineer/getall");
    xhr.send();
}

function loadEngineer(nEngineer) {
    var tr = document.createElement("tr");
    var tdId = document.createElement("td");
    var tdName = document.createElement("td");
    var tdlastName = document.createElement("td");
    tdId.innerHTML = nEngineer.id;
    tdName.innerHTML = nEngineer.name;
    tdlastName.innerHTML = nEngineer.lastname;
    tr.append(tdId,tdName,tdlastName);
    tableEngineer.appendChild(tr);
}

function loadSectores(){
    sectoresB.innerHTML = "";
    var xhr = new XMLHttpRequest();
    xhr.onloadend = function () {
        var objects = JSON.parse(xhr.responseText);
        for(let i = 0; i<objects.length;i++){
            var nSector = objects[i];
            createButton(nSector);
        }
    }
    xhr.open("GET","api/sector/getall");
    xhr.send();
}

function createButton(nSector){
    var button = document.createElement("button");
    button.className = "list-group-item list-group-item-action";
    button.id = nSector;
    button.innerHTML = nSector;
    sectoresB.appendChild(button);

    button.addEventListener("click",function (event) {
        event.preventDefault();
        button.style.backgroundColor = "#99dfb3"
        sectores.push(nSector);
        console.log(sectores);
    });
}

tableEngineer.addEventListener("click",highlight);

selectEngineer.addEventListener("click",function () {
    var id = $(".selected td:first").html();
    id = id || "No row Selected";
    idEngineer = id;
    alert("El ingeniero: "+idEngineer+" ha sido selecionado correctamente");
});

assign.addEventListener("click",function () {
    assignSector(idEngineer,sectores);
    alert("Al ingeniero: "+idEngineer+" se le ha asignado correctamente el sector seleccionado");
});

function assignSector(idEngineer,sectores){
    for(let i = 0;i<sectores.length; i++){
        let xhr = new XMLHttpRequest();
        let idSector = sectores[i];
        xhr.onloadend = function () {

        };
        xhr.open("PUT","api/sector/update/"+idSector+"/"+idEngineer);
        xhr.send();
    }
    while(sectores.length != 0){
        sectores.pop();
    }
    loadSectores();
}


function highlight(e) {
    if (selected[0]) selected[0].className = '';
    e.target.parentNode.className = 'selected';

}