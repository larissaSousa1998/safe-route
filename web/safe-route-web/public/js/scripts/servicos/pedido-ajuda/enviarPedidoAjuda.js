document.querySelectorAll(".alert").forEach(div => {
    div.addEventListener("click", enviarPedidoAjuda);
});

function enviarPedidoAjuda() {
    let dadosPromise = montarRequisicao();

    dadosPromise.then(result => {
       let xhr = new XMLHttpRequest;

       xhr.open("POST", `${BASE_URL_MESSENGER}/mensagens/enviar`, true);
       xhr.setRequestHeader("Content-Type", "application/json")

       xhr.onreadystatechange = function() { 
           if(xhr.readyState == 4 && xhr.status == 201) {
               alert("Pedidos de ajuda enviados");
           }
       }

       xhr.send(JSON.stringify(result));
    })
}

function montarRequisicao() {
    return new Promise((resolve, reject) => {
        let dadosUsuaria = JSON.parse(sessionStorage.getItem("usuaria"));

        let localizacaoPromise = getLocalizacaoUsuaria();
        localizacaoPromise.then(result => {
            resolve({
                "senderName": dadosUsuaria.nome,
                "toNumbers": getNumerosContatosUsuaria(dadosUsuaria),
                "latitudeSender": result[0],
                "longitudeSender": result[1]
            });
        })
    })
}

function getLocalizacaoUsuaria() {
    return new Promise(function(resolve, reject){
        navigator.geolocation.getCurrentPosition(function(position){
            resolve([position.coords.latitude, position.coords.longitude]);
        });
    })
}

function getNumerosContatosUsuaria(dadosUsuaria) {
    
    let contatos = dadosUsuaria.contatosConfianca;

    let numeros = [];

    contatos.forEach(contato => {
        numeros.push(tratarNumero(contato.numeroTelefone));
    });

    return numeros;
}


function tratarNumero(numeroTelefone) {
    numeroTelefone = numeroTelefone.replace(/\D/g,'')
    return "+55" + numeroTelefone;
}