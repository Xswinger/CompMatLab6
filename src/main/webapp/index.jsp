<%@ page contentType="text/html;charset=utf-8" %>

<html style="background: linear-gradient(351deg, rgba(2,0,36,1) 0%, rgba(0,156,142,1) 0%, rgba(0,212,255,1) 100%);">
<head>
    <meta charset="UTF-8">
    <title>Лабораторная работа №6</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="icon" type="image/x-icon" href="style/icon.svg">
</head>
<body>
<script src="https://polyfill.io/v3/polyfill.min.js?features=es6"></script>
<script id="MathJax-script" async src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script>
<h1 class="title is-1 has-text-centered">Численное решение ОДУ</h1>
<div>
    <div class="has-text-centered m-2">
        <div>
            <span class="title is-3">Ввод исходных данных</span>
        </div>
        <div class="columns">
            <div class="column is-one-third">
                <span class="title is-5">ОДУ</span>
                <div class="control mt-3">
                    <label class="radio">
                        <input type="radio" name="function" onchange="checkInput()">$$y'=\frac{2}{x}y+2x^3$$
                    </label>
                    <br>
                    <label class="radio">
                        <input type="radio" name="function" onchange="checkInput()">$$y'=\frac{x}{y}$$
                    </label>
                    <br>
                    <label class="radio">
                        <input type="radio" name="function" onchange="checkInput()">$$y'=e^x+y$$
                    </label>
                </div>
            </div>
            <div class="column">
                <div>
                    <span class="title is-5">Интервал</span>
                    <div class="columns">
                        <div class="column field">
                            <label class="label">Левая граница</label>
                            <div class="control">
                                <input id="left-border" class="input" type="number" onchange="checkInput()">
                            </div>
                        </div>
                        <div class="column field">
                            <label class="label">Правая граница</label>
                            <div class="control">
                                <input id="right-border" class="input" type="number" onchange="checkInput()">
                            </div>
                        </div>
                    </div>
                </div>
                <div>
                    <label class="label">Шаг</label>
                    <div>
                        <input id="step" class="input" type="number" onchange="checkInput()" min="0">
                    </div>
                </div>
                <div>
                    <label class="label">Точноcть</label>
                    <div>
                        <input id="accuracy" class="input" type="text" onchange="checkInput()">
                    </div>
                </div>
                <div>
                    <label class="label">Начальное условие</label>
                    <div>
                        <input id="start-condition" class="input" type="number" onchange="checkInput()">
                    </div>
                </div>
            </div>
        </div>
        <div class="mt-5">
            <button id="sendButton" class="button is-primary" onclick="treatmentData()" disabled>Посчитать</button>
        </div>
    </div>
    <div class="has-text-centered">
        <div class="mb-3">
            <span class="title is-3">Графики</span>
        </div>
        <div>
            <canvas class="mr-3" id="canvas_background_euler" style="height: 400px"></canvas>
        </div>
        <div>
            <canvas class="mr-3" id="canvas_background_runge" style="height: 400px"></canvas>
        </div>
        <div>
            <canvas class="mr-3" id="canvas_background_milna" style="height: 400px"></canvas>
        </div>
    </div>
</div>
<div class="has-text-centered">
    <div>
        <span class="title is-2">Результат</span>
    </div>
    <div>
        <div>
            <span class="title is-3">Приближенные значения функции</span>
        </div>
        <table class="table is-fullwidth is-bordered mt-5" id="coefficients">
            <thead>
            <tr>
                <th class="has-text-centered">X</th>
                <th class="has-text-centered">Y</th>
                <th class="has-text-centered">Усовершенствованный метод Эйлера</th>
                <th class="has-text-centered">Метод Рунге-Кутта 4-го порядка</th>
                <th class="has-text-centered">Метод Милна</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
    <div>
        <div>
            <span class="title is-3">Погрешность одношаговых методов</span>
        </div>
        <table class="table is-fullwidth is-bordered mt-5" id="error">
            <thead>
            <tr>
                <th class="has-text-centered">Усовершенствованный метод Эйлера</th>
                <th class="has-text-centered">Метод Рунге-Кутта 4-го порядка</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
    <div>
        <div>
            <span class="title is-3">Погрешность многошагового метода</span>
        </div>
        <div>
            <span id="milna-error">

            </span>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="js/controller.js"></script>
<script src="js/canvas_euler.js"></script>
</body>
</html>
