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

    if(login.indexOf("@") == -1 || login.indexOf(".com") == -1 || login.length == '' || login.length < 3 || senha.length == '' || senha.length < 6){
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

    xhr.open('POST', `${BASE_URL}/administradores/login`);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var data = xhr.responseText;
            console.log(data)
            if(data!=""){
                    armazenarUsuarioEmSessao(JSON.parse(data));
                    console.log(data);
                    window.location = "./dashboardDenuncias.html";
                    
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

document.querySelector("#submit").addEventListener("click", (event) => {
    event.preventDefault();
    validarUsuario();
});