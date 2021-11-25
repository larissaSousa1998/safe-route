function exportAllUsuarias() {
    const xhr = new XMLHttpRequest();

    xhr.open('GET', `${BASE_URL}/downloads/usuaria-comum`);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
           console.log(xhr.response);
        }else {

        }
    }
    xhr.send();
}