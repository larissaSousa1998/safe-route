function enableDarkMode() {

    var htmlTag = document.querySelector("html");
    var checkDarkMode = document.getElementById('checkDarkMode');
    if (checkDarkMode.checked) {
        htmlTag.classList.add('darkMode');
    } else {
        htmlTag.classList.remove('darkMode');
    }
}