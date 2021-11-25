function criarDocumento() {
    let dadosDocumento = {
        "tipo": document.querySelector("#registerTypeDocument").value,
        "numeracao": document.querySelector("#registerDocumentNumber").value,
    };

    armazenarDocumentoEmSessao(dadosDocumento);
}

function armazenarDocumentoEmSessao(documento) {
    sessionStorage.setItem("documento", JSON.stringify(documento));
    window.location.href = "cadastroFoto.html";
}

function armazenarFotoEmSessao(event) {
    let foto = event.target.files[0];
    let inputId = event.target.getAttribute("id");

    const reader = new FileReader();

    reader.addEventListener("load", () => {
        switch(inputId) {
            case "registerFrontDocument": 
                sessionStorage.setItem("fotoFrente", reader.result);
                break;
            case "registerBackDocument": 
                sessionStorage.setItem("fotoVerso", reader.result);
                break;
        }
    });

    reader.readAsDataURL(foto);
}

document.querySelector("#next").addEventListener("click", (event) => {
    event.preventDefault();
    criarDocumento();
});

document.querySelectorAll("input[type=file]").forEach(input => {
    input.addEventListener("change", armazenarFotoEmSessao);
});