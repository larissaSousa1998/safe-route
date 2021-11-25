function preencherCards() {
    getTotalUsuariasCadastradas();
    getTotalAnalisesPendentes();
    getTotalContasAtivas();
    getTotalContasInativas();
}

preencherCards();

function getTotalUsuariasCadastradas() {
    let xhr = new XMLHttpRequest();

    xhr.open("GET", `${BASE_URL}/usuarias/total`);

    xhr.onreadystatechange = function() {
        if(xhr.readyState == 4 && xhr.status == 200) {
            document.querySelector(".label-total-usuarias").innerHTML = xhr.responseText;
        }
    }

    xhr.send();
}

function getTotalAnalisesPendentes() {
    let xhr = new XMLHttpRequest();

    xhr.open("GET", `${BASE_URL}/usuarias/total-analise`);

    xhr.onreadystatechange = function() {
        if(xhr.readyState == 4 && xhr.status == 200) {
            document.querySelector(".label-total-contas-analise").innerHTML = xhr.responseText;
        }
    }

    xhr.send();
}

function getTotalContasAtivas() {
    let xhr = new XMLHttpRequest();

    xhr.open("GET", `${BASE_URL}/usuarias/total-ativas`);

    xhr.onreadystatechange = function() {
        if(xhr.readyState == 4 && xhr.status == 200) {
            document.querySelector(".label-ativas").innerHTML = xhr.responseText;
        }
    }

    xhr.send();
}

function getTotalContasInativas() {
    let xhr = new XMLHttpRequest();

    xhr.open("GET", `${BASE_URL}/usuarias/total-inativas`);

    xhr.onreadystatechange = function() {
        if(xhr.readyState == 4 && xhr.status == 200) {
            document.querySelector(".label-inativas").innerHTML = xhr.responseText;
        }
    }

    xhr.send();
}