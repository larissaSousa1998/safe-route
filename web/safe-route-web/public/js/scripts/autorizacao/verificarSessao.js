function verificarSessao() {
    if(sessionStorage.getItem("usuaria") == null) {
        sair();
    } else {
        jsonDadosUsuaria = JSON.parse(sessionStorage.getItem("usuaria"));

        document.querySelector("img.profile-photo").src = "data:image/png;base64,"+jsonDadosUsuaria.foto;
    }
}

window.addEventListener("load", verificarSessao);