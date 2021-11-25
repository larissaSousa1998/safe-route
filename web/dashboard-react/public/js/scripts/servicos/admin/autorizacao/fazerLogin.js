function getDadosLogin() {
    let jsonDadosLogin = {
        "email": document.querySelector("#user").value,
        "senha": document.querySelector("#passwd").value
    }

    return jsonDadosLogin;
}

function verificarUsuario() {
    const xhr = new XMLHttpRequest();

    xhr.open('POST', `${BASE_URL}/administradores/login`);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var data = xhr.responseText;

            if(data!=""){
                    armazenarUsuarioEmSessao(JSON.parse(data));
                    console.log(data);
                    window.location = "dashboardHome.html";
                    
            } else {
                alert("Senha ou Usuario incorretos");
            }
        }
    }

    xhr.send(JSON.stringify(getDadosLogin()));
}

function armazenarUsuarioEmSessao(usuaria) {
    sessionStorage.setItem("admin", JSON.stringify(usuaria));
}

document.querySelector("#submit").addEventListener("click", (event) => {
    event.preventDefault();
    verificarUsuario();
});