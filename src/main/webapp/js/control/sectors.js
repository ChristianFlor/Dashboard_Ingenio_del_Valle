const sectorContainer = document.getElementById("sectorContainer");
const key = JSON.parse(localStorage.getItem("key"));
const pages = document.getElementById("pages");

const sectorsHRef="sectors.html";
const sectorI="fas fa-fw fa-tachometer-alt";
const sectorName = "Sectors";


document.addEventListener("DOMContentLoaded", function () {
    let xhr = new XMLHttpRequest();
    xhr.onloadend = function(){
        let allowedSectors = JSON.parse(xhr.responseText);
        allowedSectors.forEach(e => {
            sectorContainer.appendChild(createSectorDisplay(e));
            requestData(e);
        });
        console.log(key.id);
        loadUser();
    }
    xhr.open("GET", "api/simulation/allowed/" + key.id);
    xhr.send()
});
function loadUser() {
    console.log(key.name);
    user.innerHTML = key.name;
    loadPagesUser(sectorsHRef,sectorI,sectorName);
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

function createSectorDisplay(sector) {
    let p1 = document.createElement("div");
    p1.className = "col mb-4";
    let p2 = document.createElement("div");
    p2.className = "card shadow mb-3";
    aCol = "<a href='#sector"+sector+"' class='d-block card-header py-3' data-toggle='collapse' role='button' aria-expanded='true' aria-controls='collapseCardExample'> <h6 class='m-0 font-weight-bold text-primary'>Sector "+sector+"</h6> </a>";
    let p3 = document.createElement("div");
    p3.className = "collapse show";
    p3.id = "sector" + sector;
    let p4 = document.createElement("div");
    p4.className = "card-body";
    createAreaChart(sector, "pH", p4);
    createAreaChart(sector, "Humidity", p4);
    createAreaChart(sector, "CO2", p4);
    createAreaChart(sector, "Temperature", p4);
    p3.appendChild(p4);
    let a = document.createElement("a");
    a.innerHTML = aCol;
    p2.appendChild(a);
    p2.appendChild(p3);
    p1.appendChild(p2);
    return p1;
}

function createAreaChart(sector,measureType,p1) {
    let h1 = document.createElement("h6");
    h1.className = "m-0 font-weight-bold text-primary";
    h1.innerHTML = "Simulation " + measureType;
    let p2 = document.createElement("div");
    p2.className = "chart-area";
    let canvas = document.createElement("canvas");
    canvas.id = measureType + "AreaChart" + sector;
    p2.appendChild(canvas);
    p1.appendChild(h1);
    p1.appendChild(p2);
    return p1;
}


function requestData(sector) {
    let xhr = new XMLHttpRequest();
    xhr.onloadend=function (){
        //data area chart
        let data = JSON.parse(xhr.responseText);
        let phData = data[0];
        let humData = data[1];
        let co2Data = data[2];
        let tempData = data[3];
        setDataAreaChart(phData, sector, "pH");
        setDataAreaChart(humData, sector, "Humidity");
        setDataAreaChart(co2Data, sector, "CO2");
        setDataAreaChart(tempData, sector, "Temperature");
    };
    xhr.open("GET","api/simulation/get/data/"+sector);
    xhr.send();
}

function setDataAreaChart(data,sector,measureType) {
    let dataLabels = [];
    for (let i = 0; i < data.length; i++) {
        dataLabels.push(i);
    }
    // AREA CHART
    let ctx = document.getElementById(measureType + "AreaChart" + sector);
    let myLineChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: dataLabels,
            datasets: [{
                label: "Value",
                lineTension: 0.3,
                backgroundColor: "rgba(78, 115, 223, 0.05)",
                borderColor: "rgba(78, 115, 223, 1)",
                pointRadius: 3,
                pointBackgroundColor: "rgba(78, 115, 223, 1)",
                pointBorderColor: "rgba(78, 115, 223, 1)",
                pointHoverRadius: 3,
                pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
                pointHoverBorderColor: "rgba(78, 115, 223, 1)",
                pointHitRadius: 10,
                pointBorderWidth: 2,
                data: data,
            }],
        },
        options: {
            maintainAspectRatio: false,
            layout: {
                padding: {
                    left: 10,
                    right: 25,
                    top: 25,
                    bottom: 0
                }
            },
            scales: {
                xAxes: [{
                    time: {
                        unit: 'date'
                    },
                    gridLines: {
                        display: false,
                        drawBorder: false
                    },
                    ticks: {
                        maxTicksLimit: 7
                    }
                }],
                yAxes: [{
                    ticks: {
                        maxTicksLimit: 5,
                        padding: 10,
                        // Include a dollar sign in the ticks
                        callback: function(value, index, values) {
                            return '' + number_format(value);
                        }
                    },
                    gridLines: {
                        color: "rgb(234, 236, 244)",
                        zeroLineColor: "rgb(234, 236, 244)",
                        drawBorder: false,
                        borderDash: [2],
                        zeroLineBorderDash: [2]
                    }
                }],
            },
            legend: {
                display: false
            },
            tooltips: {
                backgroundColor: "rgb(255,255,255)",
                bodyFontColor: "#858796",
                titleMarginBottom: 10,
                titleFontColor: '#6e707e',
                titleFontSize: 14,
                borderColor: '#dddfeb',
                borderWidth: 1,
                xPadding: 15,
                yPadding: 15,
                displayColors: false,
                intersect: false,
                mode: 'index',
                caretPadding: 10,
                callbacks: {
                    label: function(tooltipItem, chart) {
                        var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
                        return datasetLabel + ': ' + number_format(tooltipItem.yLabel);
                    }
                }
            }
        }
    });
}


Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';
function number_format(number, decimals, dec_point, thousands_sep) {
    // *     example: number_format(1234.56, 2, ',', ' ');
    // *     return: '1 234,56'
    number = (number + '').replace(',', '').replace(' ', '');
    var n = !isFinite(+number) ? 0 : +number,
        prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
        sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
        dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
        s = '',
        toFixedFix = function(n, prec) {
            var k = Math.pow(10, prec);
            return '' + Math.round(n * k) / k;
        };
    // Fix for IE parseFloat(0.55).toFixed(0) = 0;
    s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
    if (s[0].length > 3) {
        s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
    }
    if ((s[1] || '').length < prec) {
        s[1] = s[1] || '';
        s[1] += new Array(prec - s[1].length + 1).join('0');
    }
    return s.join(dec);
}