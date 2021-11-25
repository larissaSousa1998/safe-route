var map;
var directionsDisplay;
var geocoder;
var marker;
var origem;
var destino
var directionsService = new google.maps.DirectionsService();

function initialize() {	
	var latlng = new google.maps.LatLng(-18.8800397, -47.05878999999999);
	directionsDisplay = new google.maps.DirectionsRenderer();
    var options = {
        zoom: 5,
		center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    map = new google.maps.Map(document.getElementById("mapa"), options);
	directionsDisplay.setMap(map);
	directionsDisplay.setPanel(document.getElementById("trajeto-texto"));
	geocoder = new google.maps.Geocoder();
	
		navigator.geolocation.getCurrentPosition(function (position) {
			
			marker = new google.maps.Marker({
				map: map,
				draggable: true,
			});

			    markerDestino = new google.maps.Marker({
				map: map,
				draggable: true,
			});
			
			geocoder.geocode({
				"location": new google.maps.LatLng(position.coords.latitude, position.coords.longitude)
            },
            function(results, status) {
				if (status == google.maps.GeocoderStatus.OK) {
					$("#txtEnderecoPartida").val(results[0].formatted_address);
				}
            });
		});

		carregarPontosRisco();
	}

	function carregarPontosRisco(){
		let vetorCoord = [3];
		vetorCoord[1] = {lat: -23.5903235, lng: -46.39961659999999};
		vetorCoord[2] = {lat: -23.593692917398762, lng: -46.398410789475875};

		for(i=0;i<vetorCoord.length;i++){
			const circle = new google.maps.Circle({
				strokeColor: '#FF0000',
				strokeWeight: 2,
				strokeOpacity: 1,
				fillColor: '#FF0000',
				fillOpacity: .4,
				center: vetorCoord[i],
				radius: 90,
				map: map
			});
		}
	}

$(document).ready(function () {
	initialize();

	function carregarNoMapa(endereco) {
		geocoder.geocode({ 'address': endereco + ', Brasil', 'region': 'BR' }, function (results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				if (results[0]) {
					var latitude = results[0].geometry.location.lat();
					var longitude = results[0].geometry.location.lng();
					var location = new google.maps.LatLng(latitude, longitude);
					origem = location;
					
					map.setCenter(location);
					map.setZoom(16);
				}
			}
		});
	}

	function carregarNoMapaDestino(endereco) {
		geocoder.geocode({ 'address': endereco + ', Brasil', 'region': 'BR' }, function (results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				if (results[0]) {
					var latitude = results[0].geometry.location.lat();
					var longitude = results[0].geometry.location.lng();
					console.log(latitude);
					console.log(longitude);
					$('#txtEnderecoChegada').val(results[0].formatted_address);

					var location = new google.maps.LatLng(latitude, longitude);
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
						}
					});

				}
			}
		});
	}

	$("#btnEndereco1").click(function() {
		if($(this).val() != "")
			carregarNoMapa($("#txtEnderecoPartida").val());
	})


	$("#txtEnderecoPartida").blur(function() {
		if($(this).val() != "")
			carregarNoMapa($(this).val());
	});

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
			//$("#txtLatitude").val(ui.item.latitude);
			//$("#txtLongitude").val(ui.item.longitude);
			var location = new google.maps.LatLng(ui.item.latitude, ui.item.longitude);
			// marker.setPosition(location);
			map.setCenter(location);
			map.setZoom(16);
		}
	});

	// --------------------------------------------------------------------------------------------------------

	$("#btnEndereco2").click(function() {
		if($(this).val() != "")
			carregarNoMapa($("#txtEnderecoChegada").val());
	})

	$("#txtEnderecoChegada").blur(function() {
		if($(this).val() != "")
			carregarNoMapaDestino($(this).val());
	});

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
			// $("#txtLatitude").val(ui.item.latitude);
			// $("#txtLongitude").val(ui.item.longitude);
			var location = new google.maps.LatLng(ui.item.latitude, ui.item.longitude);
			// markerDestino.setPosition(location);
			map.setCenter(location);
			map.setZoom(16);
		}
	});

});