const interval = 13;

function currentTime() {
    let date = new Date();
    let hh = date.getHours();
    let mm = date.getMinutes();
    let ss = date.getSeconds();

    hh = (hh < 10) ? "0" + hh : hh + "";
    mm = (mm < 10) ? "0" + mm : mm + "";
    ss = (ss < 10) ? "0" + ss : ss + "";

    document.getElementById("clock").innerHTML = hh + ":" + mm + ":" + ss;
    setTimeout(currentTime, interval * 1000);
}
document.addEventListener('DOMContentLoaded', function() {
    currentTime();
  });
