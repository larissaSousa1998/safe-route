function getDadosPublicacao() {

    let jsonPublicacao = {
        "descricao": text_publication.value,
        "curtidas": 0,
        "titulo": title_publicacao.value,
        "usuaria": {
            "id": idUsuaria
        }
    };

    var formData = new FormData();

    formData.append('publicacao', JSON.stringify(jsonPublicacao));
    formData.append('foto', document.querySelector("#image-publicacao").files[0]);

    return formData;
}


function postPublicacao() {

    const xhr = new XMLHttpRequest();

    xhr.open('POST',`${BASE_URL}/publicacoes`);
    
    xhr.onreadystatechange = function () {
        if (xhr.status === 201 && xhr.readyState === 4) {
            let response = xhr.responseText;
            window.location = "feed.html";
        }
    };

    xhr.send(getDadosPublicacao());
}

document.querySelector('.btnCreatePublication').addEventListener('click', postPublicacao);

