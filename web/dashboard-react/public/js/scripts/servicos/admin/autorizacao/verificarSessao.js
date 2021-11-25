function verificarSessao() {
    if(sessionStorage.getItem("admin") == null) {
        sair();
    } else {
        jsonDadosAdmin = JSON.parse(sessionStorage.getItem("admin"));

        document.querySelector("img.profile-photo").src = "data:image/png;base64,"+jsonDadosAdmin.foto;
    }
}

verificarSessao();