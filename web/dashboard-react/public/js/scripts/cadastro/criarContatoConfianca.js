function criarContatoConfianca(registerKeeperName, registerKeeperEmail, registerKeeperPhone) {
    let dadosContatoConfianca = {
        "nome": document.querySelector("#registerKeeperName").value,
        "email": document.querySelector("#registerKeeperEmail").value,
        "numeroTelefone": document.querySelector("#registerKeeperPhone").value
    };

    armazenarContatoConfiancaEmSessao(dadosContatoConfianca);
}

function validarContato(){
    registerKeeperName = document.querySelector("#registerKeeperName").value,
    registerKeeperEmail = document.querySelector("#registerKeeperEmail").value,
    registerKeeperPhone = document.querySelector("#registerKeeperPhone").value

    let aux = 0;

    if (!registerKeeperName || registerKeeperName.length < 3){
        msgNome.innerHTML = "Digite um nome válido.";
    } else {
        msgNome.innerHTML = '';
        aux++;
    }
    
    if (!registerKeeperEmail || (registerKeeperEmail.indexOf('@') == -1) || (registerKeeperEmail.indexOf('.com') == -1)) {
        msgEmail.innerHTML = "Digite um e-mail válido.";
    } else {
        msgEmail.innerHTML = '';
        aux++;
    }

    if (!registerKeeperPhone){
        msgNumero.innerHTML = "Digite um número válido.";
    } else {
        msgNumero.innerHTML = '';
        aux++;
    }

    if(aux == 3){
        criarContatoConfianca(registerKeeperName, registerKeeperEmail, registerKeeperPhone);
        window.location.href = "documento.html";
    }
    
}

function mask(o, f) {
setTimeout(function () {
    var v = mphone(o.value);
    if (v != o.value) {
        o.value = v;
    }
}, 1);
}

function mphone(v) {
var r = v.replace(/\D/g,"");
r = r.replace(/^0/,"");
if (r.length > 10) {
    r = r.replace(/^(\d\d)(\d{5})(\d{4}).*/,"($1) $2-$3");
}
return r;
}

function armazenarContatoConfiancaEmSessao(contatoConfianca) {
    sessionStorage.setItem("contatoConfianca", JSON.stringify(contatoConfianca));
}

document.querySelector("#next").addEventListener("click", (event) => {
    event.preventDefault();
    validarContato();    
});