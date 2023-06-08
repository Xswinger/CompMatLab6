let sendButton = document.getElementById("sendButton")
let coefficients = document.getElementById("coefficients").getElementsByTagName('tbody')[0]
let errors = document.getElementById("error").getElementsByTagName('tbody')[0]
let functionsName = ["Эйлер", "Рунге-Кутт", "Милн"]
let tableDataX = []
let accurateDataY = []

let selectedRadio

function treatmentData() {
    tableDataX = []
    accurateDataY = []

    let left_border = document.getElementById("left-border")
    let right_border = document.getElementById("right-border")
    let step = document.getElementById("step")

    let left_border_value = Number(left_border.value)
    let right_border_value = Number(right_border.value)
    let step_value = Number(step.value)

    for (let i = 0; i <= (right_border_value - left_border_value)/step_value; i++) {
        tableDataX[i] = left_border_value + step_value * i
    }

    sendData()
}

function sendData() {

    let left_border = Number(document.getElementById("left-border").value)
    let right_border = Number(document.getElementById("right-border").value)
    let accuracy = Number(document.getElementById("accuracy").value)
    let step = Number(document.getElementById("step").value)
    let startCondition = Number(document.getElementById("start-condition").value)

    console.log(tableDataX)
    $.ajax({
        type: "POST",
        url: "./handler",
        data: {
            xData: tableDataX,
            yZero: startCondition,
            rightBorder: right_border,
            leftBorder: left_border,
            step: step,
            accuracy: accuracy,
            selectedFunction: selectedRadio
        },
        success: function (response) {
            let gsonData = JSON.parse(response);

            console.log(gsonData)

            outputData(gsonData)
        }
    })
}

function outputData(gsonData) {
    coefficients.innerHTML = ""
    errors.innerHTML = ""

    addGraphic(gsonData)

    for (let i = 0; i < tableDataX.length; i++) {

        let row = coefficients.insertRow(-1)

        let x = row.insertCell(0)
        let y = row.insertCell(1)
        let EulerCell = row.insertCell(2)
        let RungeCell = row.insertCell(3)
        let MilnaCell = row.insertCell(4)

        let xValue = document.createElement("span")
        xValue.innerText = tableDataX[i]

        let yValue = document.createElement("span")
        yValue.innerText = `${gsonData.accurateValues[i]}`

        let EulerValue = document.createElement("span")
        EulerValue.innerHTML = `${gsonData.improvedEulerMethodValues[i]}`

        let RungeValue = document.createElement("span")
        RungeValue.innerHTML = `${gsonData.rungeKutta4DegreeMethodValues[i]}`

        let MilnaValue = document.createElement("span")
        MilnaValue.innerHTML = `${gsonData.milnaMethodValues[i]}`

        x.appendChild(xValue)
        y.appendChild(yValue)
        EulerCell.appendChild(EulerValue)
        RungeCell.appendChild(RungeValue)
        MilnaCell.appendChild(MilnaValue)


        let errorRow = errors.insertRow(-1)

        let errorEuler = errorRow.insertCell(0)
        let errorRunge = errorRow.insertCell(1)

        let eulerErrorValue = document.createElement("span")
        eulerErrorValue.innerText = `${gsonData.improvedEulerMethodAccuracies[i]}`

        let rungeErrorValue = document.createElement("span")
        rungeErrorValue.innerText = `${gsonData.rungeKutta4DegreeMethodAccuracies[i]}`

        errorEuler.appendChild(eulerErrorValue)
        errorRunge.appendChild(rungeErrorValue)

    }

    let milnaError = document.getElementById("milna-error")

    milnaError.innerText = `${gsonData.milnaMethodAccuracies}`


}

function checkInput() {
    let left_border = document.getElementById("left-border")
    let right_border = document.getElementById("right-border")
    let ele = document.getElementsByName('function')
    let step = document.getElementById("step")
    let accuracy = document.getElementById("accuracy")
    let startCondition = document.getElementById("start-condition")

    let validRadio = false

    for (i = 0; i < ele.length; i++) {
        if (ele[i].checked) {
            validRadio = true
            selectedRadio = i
        }
    }

    if (Number(left_border.value) < Number(right_border.value)
        && validRadio && Number(step.value) >= 1
        && accuracy.value.trim() !== "" && !isNaN(accuracy.value)
        && startCondition.value.trim() !== "" && !isNaN(startCondition.value)) {
        left_border.className = "input"
        right_border.className = "input"
        step.className = "input"
        accuracy.className = "input"
        startCondition.className = "input"
        sendButton.disabled = false
    } else {
        left_border.className = "input is-danger"
        right_border.className = "input is-danger"
        step.className = "input is-danger"
        accuracy.className = "input is-danger"
        startCondition.className = "input is-danger"
        sendButton.disabled = true
    }
}