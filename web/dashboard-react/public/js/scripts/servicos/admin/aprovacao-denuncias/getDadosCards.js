function getTotalAreasRisco() {

    let xhr = new XMLHttpRequest();

    xhr.open("GET", `${BASE_URL}/areas-risco/total`);

    xhr.onreadystatechange = async function () {
        if(xhr.readyState === 4 && xhr.status === 200) {
            document.querySelector(".label-total-areas-risco").innerHTML = xhr.responseText;
        }
    }
    xhr.send();
}

getTotalAreasRisco();