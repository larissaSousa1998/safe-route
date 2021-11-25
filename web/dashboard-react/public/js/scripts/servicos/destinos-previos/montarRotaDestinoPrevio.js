function getLocalizacoes(e){
    setDestino(e);
    carregarRota();
}

function getLocalizacaoAtual() {
    return new Promise(function(resolve, reject){
        navigator.geolocation.getCurrentPosition(function(position){
            resolve([position.coords.latitude, position.coords.longitude]);
        });
    })
}

function carregarRota() {
    let promiseLocalizacao = getLocalizacaoAtual();

    promiseLocalizacao.then(function (result){
        let partida = {
            "latitude": result[0],
            "longitude": result[1]
        };
        let destinoPromise = converterEnderecoEmCoordernadas(
            document.getElementById("input-destination").value
        );
        let promiseConverter = converterCoordernadas(partida.latitude, partida.longitude);

        Promise.all([destinoPromise, promiseConverter]).then(function(results) {
            montarRota(partida, results[0], "destino-previo");
            document.getElementById("input-current-location").value = results[1];
        });
    });
};

function setDestino(e){
    let element = e.target;
    var destination;
    if(element.classList.contains("choice-route")){
        destination = element.childNodes[1].childNodes[3].innerHTML;
    } else if(element.classList.contains("icon-walk")) {
        element = element.parentElement;
        destination = element.childNodes[1].childNodes[3].innerHTML;
    } else if(element.classList.contains("text")) {
        console.log(element.childNodes);
        destination = element.childNodes[3].innerHTML;
    } else {
        element = element.parentElement.parentElement;
        destination = element.childNodes[1].childNodes[3].innerHTML;
    }

    document.getElementById("input-destination").value = destination;
}