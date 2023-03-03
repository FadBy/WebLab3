function showError(element, message) {
    removeError();
    element.append($("<div></div>").attr("id", "error").addClass("error").html(message));
}

function removeError() {
    $("#error").remove();
}

let unitCanvas;

let rLength;
let axisLength;
let arrowTipLength;
let arrowAngle;
let markLength;
let textDist;
let vertTextPos;
let horTextPos;
let textOffset;
let radiusInactiveBorder;
let dotRadius;
let oX;
let oY;


$(document).ready(function() {

    const canvas = document.getElementById("canvas");
    const ctx = canvas.getContext("2d");

    unitCanvas = canvas.width / 100;

    rLength = unitCanvas * 33;
    axisLength = unitCanvas * 90;
    arrowTipLength = unitCanvas * 5;
    arrowAngle = Math.PI / 12;
    markLength = unitCanvas * 2;
    textDist = unitCanvas * 2;
    vertTextPos = 1;
    horTextPos = -1;
    textOffset = canvas.style.fontSize.length * 0.5;
    radiusInactiveBorder = 5;
    dotRadius = 3;
    oX = canvas.height / 2;
    oY = canvas.width / 2;

    drawCanvas();
    drawCircles();
    
    $("#canvas").click(function (event) {
        const x = event.pageX - $(this).position().left - 27;
        const y = event.pageY - $(this).position().top - 27;
        let hidden_x_input = document.getElementById("hidden_form:hidden_x_input");
        let hidden_y_input = document.getElementById("hidden_form:hidden_y_input");
        let hidden_r_input = document.getElementById("hidden_form:hidden_r_input");

        var inputElement = document.getElementById("form:r_input_input");
        var ariaValueNow = inputElement.getAttribute("aria-valuenow");

        let r_input = ariaValueNow;
        hidden_x_input.value = ((x - canvas.width / 2) / rLength * r_input).toFixed(2);
        hidden_y_input.value = (-(y - canvas.height / 2) / rLength * r_input).toFixed(2);
        hidden_r_input.value = r_input;
        document.getElementById('hidden_form:hidden_submit_command').click();
    });
});

function drawCanvas() {
    const canvas = document.getElementById("canvas");
    const ctx = canvas.getContext("2d");
    ctx.fillStyle = "#BCBA30";
    //drawing rectangle
    ctx.fillRect(oX, oY, rLength, rLength);
    //drawing triangle
    ctx.lineWidth = 0;
    ctx.beginPath();
    ctx.moveTo(oX, oY);
    ctx.lineTo(oX, oY - rLength);
    ctx.lineTo(oX - rLength, oY);
    ctx.lineTo(oX, oY);
    ctx.fill();

    //drawing circle
    ctx.moveTo(oX - rLength / 2, oY);
    ctx.lineTo(oX, oY);
    ctx.lineTo(oX, oY + rLength / 2);
    ctx.arc(oX, oY, rLength / 2, Math.PI / 2, Math.PI);
    ctx.fill();

    ctx.strokeStyle = "#D4D0B9"
    ctx.beginPath();
    //drawing vertical arrow
    ctx.moveTo(oX, oY + axisLength / 2);
    ctx.lineTo(oX, oY - axisLength / 2);
    ctx.lineTo(oX - arrowTipLength * Math.tan(arrowAngle), oY - axisLength / 2 + arrowTipLength);
    ctx.moveTo(oX + arrowTipLength * Math.tan(arrowAngle), oY - axisLength / 2 + arrowTipLength);
    ctx.lineTo(oX, oY - axisLength / 2);

//drawing horizontal arrow
    ctx.moveTo(oX - axisLength / 2, oY);
    ctx.lineTo(oX + axisLength / 2, oY);
    ctx.lineTo(oX + axisLength / 2 - arrowTipLength, oY - arrowTipLength * Math.tan(arrowAngle));
    ctx.moveTo(oX + axisLength / 2 - arrowTipLength, oY + arrowTipLength * Math.sin(arrowAngle));
    ctx.lineTo(oX + axisLength / 2, oY);

    //drawing marks on vertical
    //-R
    ctx.moveTo(oX - markLength / 2, oY + rLength);
    ctx.lineTo(oX + markLength / 2, oY + rLength);
    ctx.strokeText("-R", oX + vertTextPos * (markLength / 2 + textDist), oY + rLength + textOffset);
    //-R/2
    ctx.moveTo(oX - markLength / 2, oY + rLength / 2);
    ctx.lineTo(oX + markLength / 2, oY + rLength / 2);
    ctx.strokeText("-R/2", oX + vertTextPos * (markLength / 2 + textDist), oY + rLength / 2 + textOffset);
    //R/2
    ctx.moveTo(oX - markLength / 2, oY - rLength / 2);
    ctx.lineTo(oX + markLength / 2, oY - rLength / 2);
    ctx.strokeText("R/2", oX + vertTextPos * (markLength / 2 + textDist), oY - rLength / 2 + textOffset);

    //R
    ctx.moveTo(oX - markLength / 2, oY - rLength);
    ctx.lineTo(oX + markLength / 2, oY - rLength);
    ctx.strokeText("R", oX + vertTextPos * (markLength / 2 + textDist), oY - rLength + textOffset);
    ctx.strokeText("y", oX + vertTextPos * (markLength / 2 + textDist), oY - axisLength / 2 + textOffset);

    //drawing marks on horizontal
    //-R
    ctx.moveTo(oX - rLength, oY - markLength / 2);
    ctx.lineTo(oX - rLength, oY + markLength / 2);
    ctx.strokeText("-R", oX - rLength - textOffset, oY + horTextPos * (markLength / 2 + textDist));
    //-R/2
    ctx.moveTo(oX - rLength / 2, oY - markLength / 2);
    ctx.lineTo(oX - rLength / 2, oY + markLength / 2);
    ctx.strokeText("-R/2", oX - rLength / 2 - textOffset, oY + horTextPos * (markLength / 2 + textDist));

    //R/2
    ctx.moveTo(oX + rLength / 2, oY - markLength / 2);
    ctx.lineTo(oX + rLength / 2, oY + markLength / 2);
    ctx.strokeText("R/2", oX + rLength / 2 - textOffset, oY + horTextPos * (markLength / 2 + textDist));

    //R
    ctx.moveTo(oX + rLength, oY - markLength / 2);
    ctx.lineTo(oX + rLength, oY + markLength / 2);
    ctx.strokeText("R", oX + rLength - textOffset, oY + horTextPos * (markLength / 2 + textDist));
    ctx.strokeText("x", oX + axisLength / 2 - textOffset, oY + horTextPos * (markLength / 2 + textDist));

    ctx.stroke();
}

function clearCanvas() {
    document.getElementById("canvas").getContext("2d").clearRect(0, 0, canvas.width, canvas.height);
}

function drawCircle(x, y, r, hit) {
    ctx = document.getElementById("canvas").getContext("2d");
    ctx.beginPath();
    if (hit) {
        ctx.fillStyle = "#3F2900";
    } else {
        ctx.fillStyle = "#BCBA30";
    }
    ctx.arc(5 * unitCanvas + axisLength / 2 + x / r * rLength, 5 * unitCanvas + axisLength / 2 - y / r * rLength, 3, 0, 2 * Math.PI, false);
    ctx.fill();
}

function drawCircles() {
    var table = document.getElementById("table");

    var tableRows = table.getElementsByTagName("tr");

    let current_r = -1;

    for (var i = 0; i < tableRows.length; i++) {
        var cells = tableRows[i].getElementsByTagName("td");

        for (var j = 0; j < cells.length; j++) {
            let x = cells[0].innerHTML;
            let y = cells[1].innerHTML;
            let r = cells[2].innerHTML;
            let hitResult = cells[3].innerHTML;
            if (current_r != r) {
                current_r = r;
                clearCanvas();
                drawCanvas();
            }
            drawCircle(parseFloat(x), parseFloat(y), parseFloat(r), hitResult == "true" ? true : false);
        }
    }
    // setTimeout(drawCircles, 100);
}
    




