let canvas_euler = document.getElementById("canvas_background_euler")
let canvas_runge = document.getElementById("canvas_background_runge")
let canvas_milna = document.getElementById("canvas_background_milna")
let chart_euler
let chart_runge
let chart_milna

$(document).ready(function () {
    chart_euler = new Chart(canvas_euler, {
        type: 'line',
        data: {
            labels: [],
            datasets: [{
                label: 'Начальный график',
                data: [],
                backgroundColor: 'rgb(0,255,0)',
                borderColor: 'rgb(13,61,0)',
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: false
                    }
                }]
            }
        }
    })
    chart_runge = new Chart(canvas_runge, {
        type: 'line',
        data: {
            labels: [],
            datasets: [{
                label: 'Начальный график',
                data: [],
                backgroundColor: 'rgb(0,255,0)',
                borderColor: 'rgb(13,61,0)',
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: false
                    }
                }]
            }
        }
    })
    chart_milna = new Chart(canvas_milna, {
        type: 'line',
        data: {
            labels: [],
            datasets: [{
                label: 'Начальный график',
                data: [],
                backgroundColor: 'rgb(0,255,0)',
                borderColor: 'rgb(13,61,0)',
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: false
                    }
                }]
            }
        }
    })
})

function addGraphic(data) {

    let dataYEuler = []
    let dataYRunge = []
    let dataYMilna = []
    let currentDataY = []

    for (let i = 0; i < tableDataX.length; i++) {
        dataYEuler[i] = data.improvedEulerMethodValues[i]
        dataYRunge[i] = data.rungeKutta4DegreeMethodValues[i]
        dataYMilna[i] = data.milnaMethodValues[i]
        currentDataY[i] = data.accurateValues[i]
    }

    const data_euler = {
        labels: tableDataX,
        datasets: [{
            label: "Начальный",
            data: currentDataY,
            backgroundColor: 'rgba(175,0,39,0.8)',
            borderColor: 'rgb(255,237,34)',
            borderWidth: 1
        }, {
            label: functionsName[0],
            data: dataYEuler,
            backgroundColor: 'rgb(0,255,0)',
            borderColor: 'rgb(255,237,34)',
            borderWidth: 1
        }]
    };

    const data_runge = {
        labels: tableDataX,
        datasets: [{
            label: "Начальный",
            data: currentDataY,
            backgroundColor: 'rgba(175,0,39,0.8)',
            borderColor: 'rgb(255,237,34)',
            borderWidth: 1
        }, {
            label: functionsName[1],
            data: dataYRunge,
            backgroundColor: 'rgb(245,99,255)',
            borderColor: 'rgb(255,237,34)',
            borderWidth: 1
        },]
    };

    const data_milna = {
        labels: tableDataX,
        datasets: [{
            label: "Начальный",
            data: currentDataY,
            backgroundColor: 'rgba(175,0,39,0.8)',
            borderColor: 'rgb(255,237,34)',
            borderWidth: 1
        }, {
            label: functionsName[2],
            data: dataYMilna,
            backgroundColor: 'rgb(255,115,0)',
            borderColor: 'rgb(255,237,34)',
            borderWidth: 1
        }]
    };

    chart_euler.data = data_euler
    chart_runge.data = data_runge
    chart_milna.data = data_milna

    chart_euler.update()
    chart_runge.update()
    chart_milna.update()

}