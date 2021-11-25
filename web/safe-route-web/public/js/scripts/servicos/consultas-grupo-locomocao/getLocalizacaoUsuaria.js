window.onload = function() {
    var watchId = navigator.geolocation.watchPosition(function(position) {
        latitude = position.coords.latitude;
        longitude = position.coords.longitude;

        geocoder.geocode({
            "location": new google.maps.LatLng(position.coords.latitude, position.coords.longitude)
        },
        function(results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                criarLocalização(latitude, longitude);
            }
        });
    });
};

function criarLocalização(latitude, longitude) {
    let dadosLocalização = {
        "latitude": latitude,
        "longitude": longitude,
    };

    armazenarLocalizacaoEmSessao(dadosLocalização);
}

function armazenarLocalizacaoEmSessao(dados) {
    sessionStorage.setItem("localizacao", JSON.stringify(dados));
    console.log(dados);
}