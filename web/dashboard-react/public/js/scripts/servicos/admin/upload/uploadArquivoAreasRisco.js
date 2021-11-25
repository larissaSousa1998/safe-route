function montarRequisicao() {
    var formData = new FormData();

    formData.append("arquivo", document.querySelector("#importFile").files[0]);

    return formData;
}

document.querySelector("#createGroup").addEventListener("click", uploadArquivoAreasRisco);

function uploadArquivoAreasRisco() {
    const xhr = new XMLHttpRequest();

    xhr.open('POST',`${BASE_URL}/denuncias-local/anexo`);
    
    xhr.onreadystatechange = function () {
        if (xhr.status === 201 && xhr.readyState === 4) {
            let response = xhr.responseText;
            window.location.reload();
        }
    };

    xhr.send(montarRequisicao());
}