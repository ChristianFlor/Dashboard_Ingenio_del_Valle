const tableEngineer = document.getElementById("tableEngineer");
const selectEngineer = document.getElementById("selectEngineer");
const selected = tableEngineer.getElementsByClassName('selected');
const sectoresB = document.getElementById("sectoresB");
const assign = document.getElementById("assign");

var idEngineer;
var sectores = [];

document.addEventListener("DOMContentLoaded",init);

function init(){
    loadTableEngineer();
    loadSectores();
}

function loadTableEngineer() {
    tableEngineer.innerHTML = "";
    var xhr = new XMLHttpRequest();
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
    console.log(nSector);
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
    alert(id);
});

assign.addEventListener("click",function () {
    assignSector(idEngineer,sectores);
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
/*
function fnselect(){
    var $row=$(this).parent().find('td');
    var clickeedID=$row.eq(0).text();
    // alert(clickeedID);
}*/


