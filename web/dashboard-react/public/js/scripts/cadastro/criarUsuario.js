function criarUsuario(nome, email, senha, dataNasc) {
    let dadosUsuario = {
        "nome": nome,
        "email": email,
        "senha": senha,
        "dataNascimento": dataNasc,
    };

    armazenarUsuarioEmSessao(dadosUsuario);
}

function validarUsuario(){
    let nome =  document.querySelector("#registerName").value
    let email =  document.querySelector("#registerEmail").value
    let senha =  document.querySelector("#registerPasswd").value
    let dataNasc =  document.querySelector("#registerBirthdate").value

    let aux = 0;

    if(nome.length < 3 || nome.length == ''){
        msgNome.innerHTML = "Digite um nome válido";
    }else {
        msgNome.innerHTML = '';
        aux++;
    }

    if(email.indexOf("@") == -1 || email.indexOf(".com") == -1 || email.length == '' || email.length < 3){
        msgEmail.innerHTML = "Digite um email válido";
    }else {
        msgEmail.innerHTML = '';
        aux++;
    }

    if(senha.length == '' || senha.length < 8){
        msgSenha.innerHTML = "Digite uma senha com o mínimo de 8 caracteres";
    }else {
        msgSenha.innerHTML = '';
        aux++;
    }

    if(dataNasc.length == '' || dataNasc.length < 10){
        msgData.innerHTML = "Digite uma data válida";
    }else {
        var hoje = new Date().toDateString();
        var data2 = new Date(dataNasc);
        console.log(data2);
        console.log(hoje);
        if(data2 > hoje){
            msgData.innerHTML = "Digite uma data válida";
        }else {
            msgData.innerHTML = '';
            aux++;
        }
    }

    if(aux == 4){
        criarUsuario(nome, email, senha, dataNasc);
        window.location.href = "destinoPrevio.html";
    }
    
}

function armazenarUsuarioEmSessao(usuario) {
    sessionStorage.setItem("usuaria", JSON.stringify(usuario));
}

function limparCampo(msg){
    msg.innerHTML = "";
}

document.querySelector("#next").addEventListener("click", (event) => {
    event.preventDefault();
    validarUsuario();
});