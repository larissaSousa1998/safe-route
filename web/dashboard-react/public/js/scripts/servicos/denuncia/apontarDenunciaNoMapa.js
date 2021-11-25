let map;

function initialize() {

	geocoder = new google.maps.Geocoder();

    options = {
        zoom: 5,
        center: new google.maps.LatLng("-23.5489", "-46.6388")
    }

    map = new google.maps.Map(document.getElementById("mapa"), options);

	navigator.geolocation.getCurrentPosition(function (position) {
        var latlng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
        var options = {
            zoom: 16,
            center: latlng,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
    
        map = new google.maps.Map(document.getElementById("mapa"), options);
	});
}

let markers = [];

function apontarDenunciaNoMapa() {
    let local = document.getElementById("localDenuncia").value;
    let coordenadasPromise = converterEnderecoEmCoordernadas(local);
    limparMarcadores();

    coordenadasPromise.then(function (result){
        marker = new google.maps.Marker({
			map: map,
            position:new google.maps.LatLng(result.latitude, result.longitude)
		});
        markers.push(marker);
        map.setZoom(16);
        map.setCenter(new google.maps.LatLng(result.latitude, result.longitude));
    });
}

function limparMarcadores(){
    for (let i = 0; i < markers.length; i++){
        markers[i].setMap(null);
    }
}

//autocomplete no imput de endereco da denuncia
$(document).ready(function () {
    $("#localDenuncia").autocomplete({
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
        }
    });
});

document.getElementById("localDenuncia").addEventListener("blur",apontarDenunciaNoMapa);
