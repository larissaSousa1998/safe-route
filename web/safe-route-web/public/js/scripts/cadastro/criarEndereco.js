let estado;

function criarEndereco() {
    let dadosEndereco = {
        "descricao": document.querySelector("#registerDescription").value,
        "logradouro": document.querySelector("#registerPlace").value,
        "numero": document.querySelector("#registerNumber").value,
        "cep": document.querySelector("#registerCep").value,
        "estado": estado
    };

    
    armazenarEnderecoEmSessao(dadosEndereco);
}

function buscaDadosPorCep(cep) {
    let xhr = new XMLHttpRequest();

    xhr.open("GET", `https://viacep.com.br/ws/${cep}/json/`);

    xhr.onreadystatechange = function () {
        if (xhr.status === 200 && xhr.readyState === 4) {
            let response = xhr.responseText;
            
            let localeData = JSON.parse(response);

            estado = localeData.uf;
            document.querySelector("#registerPlace").value = localeData.logradouro;
        }
    };

    xhr.send();
}

document.querySelector("#registerCep").addEventListener("blur", function () {
    buscaDadosPorCep(document.querySelector("#registerCep").value);
});

function validarEndereco(){
    registerDescription = document.querySelector("#registerDescription").value,
    registerPlace = document.querySelector("#registerPlace").value,
    registerNumber = document.querySelector("#registerNumber").value,
    registerCep  = document.querySelector("#registerCep").value

    let aux = 0;

    if(registerDescription.length < 3 || registerDescription.length == ''){
        msgDesc.innerHTML = "Digite uma descrição válida.";
    }else {
        msgDesc.innerHTML = '';
        aux++;
    }

    if(registerPlace.length < 5 || registerPlace.length == ''){
        msgPublicPlace.innerHTML = "Digite um endereço válido.";
    }else {
        msgPublicPlace.innerHTML = '';
        aux++;
    }

    if(registerNumber.length == ''){
        msgNum.innerHTML = "Digite uma número válido.";
    }else {
        msgNum.innerHTML = '';
        aux++;
    }

    if(registerCep.length == '' || registerCep.length < 9){
        msgCep.innerHTML = "Digite um número válido.";
    }else {
        msgCep.innerHTML = '';
        aux++;
    }

    if(aux == 4){
        criarEndereco(registerDescription, registerPlace, registerNumber, registerCep);
        window.location.href = "contatoDeConfianca.html";
    }

}

function limparCampo(msg){
    msg.innerHTML = "";
}

function armazenarEnderecoEmSessao(endereco) {
    sessionStorage.setItem("endereco", JSON.stringify(endereco));
}

document.querySelector("#next").addEventListener("click", (event) => {
    event.preventDefault();
    validarEndereco();
});