function getValoresDenuncia(){

    let local = document.getElementById("localDenuncia").value;
    let fonte = document.getElementById("fonteDenuncia").value;
    let descricao = document.getElementById("descricaoDenuncia").value;
    let denuncia = {
        "local": local,
        "fonte": fonte,
        "descricao": descricao,
        "usuaria": {
            "id": idUsuaria
        }
    }

    return denuncia;

};

function postDenuncia(event) {
    event.preventDefault();
    let denuncia = getValoresDenuncia();

    const xhr = new XMLHttpRequest();
    xhr.open('POST',`${BASE_URL}/denuncias-local`);
    
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function() {
        if (xhr.status === 201 && xhr.readyState === 4) {
            let response = xhr.responseText;

            alert("Denuncia enviada com sucesso");
            window.location.reload();
        }
    }
    xhr.send(JSON.stringify(denuncia));
};

document.querySelector(".btnDenuncia").addEventListener("click",postDenuncia);