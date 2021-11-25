function montarRota(partida,destino, tipoRota){
    carregarPontoPartida(partida);
    carregarPontoDestino(destino, tipoRota);
}

function carregarPontoPartida(partida) {

    var location = new google.maps.LatLng(partida.latitude, partida.longitude);
    origem = location;

    map.setCenter(location);
    map.setZoom(16);

}

function carregarPontoDestino(endereco, tipoRota) {

    document.querySelectorAll(`.btn-defined-destination`).forEach(el => {
        el.classList.add("noneImportant");
    });

    let promise = converterCoordernadas(endereco.latitude, endereco.longitude);

    // promise.then(function (result) {
    //     $('#input-destination').val(result);
    // });      
    
    var location = new google.maps.LatLng(endereco.latitude, endereco.longitude);
    destino = location;
    
    let request = {
        origin:origem,
        destination: destino,
        travelMode: 'WALKING',
        provideRouteAlternatives: true,
    }

    directionsService = new google.maps.DirectionsService();
    directionsService.route(request,function(result,status) {
        if (status == google.maps.DirectionsStatus.OK) { // Se deu tudo certo
            for (var i = 0; i < result.routes.length; i++) {
                var dr = new google.maps.DirectionsRenderer();
                dr.setDirections(result);
                // Tell the DirectionsRenderer which route to display
                dr.setRouteIndex(i);
                dr.setMap(map);
            }
            directionsDisplay.setDirections(result); // Renderizamos no mapa o resultado
            
            define.classList.add("noneImportant");
            trajeto.classList.remove("none");
            buttonSetRoute.classList.add("add-color-btn");

            setTimeout(() => {
                if(document.querySelector(".adp-list") != null) {
                    let adp = document.querySelector(".adp");
                    adp.classList.add("noneImportant");
                    document.querySelectorAll(`.btn-defined-destination`).forEach(el => {
                        el.classList.remove("noneImportant");
                    });
                }
            },100);
        }
    });

    let div;

    switch(tipoRota) {
        case "destino-previo": 
            div = "div#trajeto-texto2";
            break;
        case "grupo-locomocao":
            div = "div#trajeto-texto3";
            break;
        default:  
            div = "div#trajeto-texto";
            break;
    }

    directionsDisplay.setPanel(document.querySelector(div));
}