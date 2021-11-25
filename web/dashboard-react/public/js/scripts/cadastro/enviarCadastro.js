function getDadosCadastro() {
    let jsonDadosUsuario = {
        "usuarioComum" : 
            JSON.parse(sessionStorage.getItem("usuaria")),
        "endereco" : 
            JSON.parse(sessionStorage.getItem("endereco")),
        "contatoConfianca" : 
            JSON.parse(sessionStorage.getItem("contatoConfianca")),
        "documento" : 
            JSON.parse(sessionStorage.getItem("documento"))
    };

    var formData = new FormData();

    formData.append('cadastro', JSON.stringify(jsonDadosUsuario));
    formData.append('selfie', document.querySelector("#registerSelfie").files[0]);
    formData.append('fotoFrente', dataURLtoFile(sessionStorage.getItem("fotoFrente"), "fotoFrente.png"));
    formData.append('fotoVerso', dataURLtoFile(sessionStorage.getItem("fotoVerso"), "fotoVerso.png"));

    return formData;
}

function postDadosUsuaria() {

    const xhr = new XMLHttpRequest();

    xhr.open('POST',`${BASE_URL}/usuarias`);
    //  xhr.setRequestHeader("Content-Type", "multipart/form-data")
    
    xhr.onreadystatechange = function () {
        if (xhr.status === 201 && xhr.readyState === 4) {
            let response = xhr.responseText;

            alert("Cadastrada com sucesso!");
            window.location = "index.html";
        }
    };

    xhr.send(getDadosCadastro());
}

document.querySelector("#next").addEventListener("click", (event) => {
    event.preventDefault();
    postDadosUsuaria();
});

function dataURLtoFile(dataurl, filename) {
 
    var arr = dataurl.split(','),
        mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]), 
        n = bstr.length, 
        u8arr = new Uint8Array(n);
        
    while(n--){
        u8arr[n] = bstr.charCodeAt(n);
    }
    
    return new File([u8arr], filename, {type:mime});
}