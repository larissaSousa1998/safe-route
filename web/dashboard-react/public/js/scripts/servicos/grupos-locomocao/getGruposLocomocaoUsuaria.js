function getGruposLocomocaoUsuariaParticipa(id) {
    return new Promise(function(resolve, reject) {
        let xhr = new XMLHttpRequest();

        xhr.open("GET", `${BASE_URL}/grupos-locomocao/participante/${id}`);

        xhr.onreadystatechange = function () {
            if(xhr.status === 200 && xhr.readyState === 4) {
                let grupos = JSON.parse(xhr.responseText);

                resolve(grupos);

            }
        }

        xhr.send();
    });
}