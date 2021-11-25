function getTotalContasAtivasAprovadas() {
    return new Promise(function (resolve, reject) {
        const xhr = new XMLHttpRequest();

        xhr.open('GET', `${BASE_URL}/usuarias/total/ativas-aprovadas`);
        xhr.setRequestHeader("Content-Type", "application/json");

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                resolve(xhr.response);
            }
        }
        xhr.send();
    });
}

function getTotalContasInativasAprovadas() {
    return new Promise(function (resolve, reject) {
        const xhr = new XMLHttpRequest();

        xhr.open('GET', `${BASE_URL}/usuarias/total/inativas-aprovadas`);
        xhr.setRequestHeader("Content-Type", "application/json");

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                resolve(xhr.response);
            }
        }
        xhr.send();
    });
}

function preencherCards() {
    let totalContasAtivasAprovadas = getTotalContasAtivasAprovadas();
    let totalContasInativasAprovadas = getTotalContasInativasAprovadas();

    Promise.all([totalContasAtivasAprovadas, totalContasInativasAprovadas]).then(values => {
        let ativasAprovadas = values[0];
        let inativasAprovadas = values[1];

        document.querySelector(".label-ativas-aprovadas").innerHTML = ativasAprovadas;
        document.querySelector(".label-inativas-aprovadas").innerHTML = inativasAprovadas;
    })
}

preencherCards();