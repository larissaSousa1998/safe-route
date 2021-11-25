
var checkDarkMode = document.getElementById('checkDarkMode');
let logo = document.getElementById('logo-type');

function enableDarkMode() {
    if (checkDarkMode.checked) {
        let darkmode = true;
        sessionStorage.setItem("darkmode", darkmode);
    } else {
        let darkmode = false;
        sessionStorage.setItem("darkmode", darkmode);
    } 
    confirm();
}

function confirm(){
    let flag = sessionStorage.getItem('darkmode');
    var htmlTag = document.querySelector("html");

    if(flag == "false"){
        htmlTag.classList.remove('darkMode');
        logo.src = '../image/logotype.png';
    } else {
        checkDarkMode.checked = true;
        htmlTag.classList.add('darkMode');
        logo.src = '../image/logotype-ligth.png';
    }
}

confirm();



