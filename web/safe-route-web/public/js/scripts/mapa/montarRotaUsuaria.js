function carregarRotaUsuaria() {
    let promiseLocalizacao = converterEnderecoEmCoordernadas(
        document.getElementById("txtEnderecoPartida").value
    ); 

    promiseLocalizacao.then(function (result){
        let destinoPromise = converterEnderecoEmCoordernadas(
            document.getElementById("txtEnderecoChegada").value
        );
        let partida = result;
        destinoPromise.then(function(result){
            montarRota(partida, result, "normal");
        });
    });
};

// $("#btnEndereco1").click(function() {
//     if($(this).val() != "")
//         carregarRotaUsuaria($("#txtEnderecoPartida").val());
// });


// $("#txtEnderecoPartida").blur(function() {
//     if($(this).val() != "")
//         carregarRotaUsuaria($(this).val());
// });

$("#txtEnderecoPartida").autocomplete({
    source: function (request, response) {
        geocoder.geocode({ 'address': request.term + ', Brasil', 'region': 'BR' }, function (results, status) {
            response($.map(results, function (item) {
                return {
                    label: item.formatted_address,  
                    value: item.formatted_address,
                    latitude: item.geometry.location.lat(),
                    longitude: item.geometry.location.lng()
                }
            }));
        })
    },
    select: function (event, ui) {
        var location = new google.maps.LatLng(ui.item.latitude, ui.item.longitude);
        map.setCenter(location);
        map.setZoom(16);
        if($("#txtEnderecoChegada").val() != ""){
            carregarRotaUsuaria();
        }
    }
});

// --------------------------------------------------------------------------------------------------------

// $("#btnEndereco2").click(function() {
//     if($(this).val() != "")

//         carregarRotaUsuaria($("#txtEnderecoChegada").val());
// })

// $("#txtEnderecoChegada").blur(function() {
//     if($(this).val() != "")
//         carregarRotaUsuaria($(this).val());
// });

$("#txtEnderecoChegada").autocomplete({
    source: function (request, response) {
        geocoder.geocode({ 'address': request.term + ', Brasil', 'region': 'BR' }, function (results, status) {
            response($.map(results, function (item) {
                return {
                    label: item.formatted_address,  
                    value: item.formatted_address,
                    latitude: item.geometry.location.lat(),
                    longitude: item.geometry.location.lng()
                }
            }));
        })
    },
    select: function (event, ui) {
        var location = new google.maps.LatLng(ui.item.latitude, ui.item.longitude);
        map.setCenter(location);
        map.setZoom(16);
        carregarRotaUsuaria();
    }
});