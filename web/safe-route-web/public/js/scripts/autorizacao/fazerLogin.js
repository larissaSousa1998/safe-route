function getDadosLogin() {
    let jsonDadosLogin = {
        "email": document.querySelector("#user").value,
        "senha": document.querySelector("#passwd").value
    }

    return jsonDadosLogin;
}

function validarUsuario(){
    let login = document.querySelector("#user").value;
    let senha = document.querySelector("#passwd").value;
    let aux = 0;

    if(login.indexOf("@") == -1 || login.indexOf(".com") == -1 || login.length == '' || login.length < 3 || senha.length == '' || senha.length < 8){
        validationMessage.innerHTML = "E-mail ou senha inválidos";
    }else {
        validationMessage.innerHTML = '';
        aux++;
    }

    if(aux == 1){
        verificarUsuario();
    }
}

function limparCampo(msg){
    msg.innerHTML = "";
}

function verificarUsuario() {
    const xhr = new XMLHttpRequest();

    xhr.open('POST', `${BASE_URL}/usuarias/login`);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var data = xhr.responseText;

            if(data!=""){
                let userData = JSON.parse(data);

                if(userData.aprovada && userData.ativa) {
                    armazenarUsuarioEmSessao(JSON.parse(data));
                    window.location = "home.html";
                } else if(userData.aprovada && !userData.ativa) {
                    let result = confirm("Olá! Sua conta foi desativada anteriormente. Deseja reativa-la?");
                    if(result) {
                        reativarConta(userData);
                    } else {
                        alert("OK! Não reativaremos sua conta! Esperamos que volte logo :)")
                    }
                } else {
                    alert("Os dados de sua conta ainda está no processo de validação! Te notificaremos via email assim que terminarmos :)");
                }
            } else {
                alert("Senha ou Usuario incorretos");
            }
        } 

    }

    xhr.send(JSON.stringify(getDadosLogin()));
}

function reativarConta(userData) {
    const xhr = new XMLHttpRequest();

    xhr.open('PUT', `${BASE_URL}/usuarias/ativar-conta/${userData.id}`);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            armazenarUsuarioEmSessao(userData);
            window.location = "home.html";
        }
    }

    xhr.send();
}

function armazenarUsuarioEmSessao(usuaria) {
    sessionStorage.setItem("usuaria", JSON.stringify(usuaria));
}

darkmode = false;
sessionStorage.setItem("darkmode", darkmode);


document.querySelector("#submit").addEventListener("click", (event) => {
    event.preventDefault();
    validarUsuario();
});