var map;
var directionsDisplay;
var geocoder;
var marker;
var origem;
var destino;
var directionsService = new google.maps.DirectionsService();
let auxPanel = 0;



let define = document.querySelector(".waiting-route");
let trajeto = document.getElementById("trajeto-texto");

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
	directionsDisplay.setPanel(document.querySelector("#trajeto-texto"));

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
	let xhr = new XMLHttpRequest();
	let vetorCoord = [];
	let vetAux = [];
	xhr.open("GET", `${BASE_URL}/areas-risco`);
	
	xhr.onreadystatechange = function () {
		if (xhr.status === 200 && xhr.readyState === 4) {
			let response = xhr.responseText;
			vetorCoord = JSON.parse(response);

			for(i=0;i<vetorCoord.length;i++){
				vetAux = {lat: parseFloat(vetorCoord[i].latitude), lng: parseFloat(vetorCoord[i].longitude)}
				const circle = new google.maps.Circle({
					strokeColor: '#FF0000',
					strokeWeight: 2,
					strokeOpacity: 1,
					fillColor: '#FF0000',
					fillOpacity: .4,
					center: vetAux,
					radius: 90,
					map: map
				});
			}
		}
	};
	xhr.send();

}

let aux = "";
function trocarEndereco(){
	
	let partida = $("#txtEnderecoPartida").val();
	let chegada = $("#txtEnderecoChegada").val();
	aux = $("#txtEnderecoPartida").val();
	$("#txtEnderecoPartida").val(chegada);
	$("#txtEnderecoChegada").val(aux);
}


$(document).ready(function () {
	initialize();
});

let buttonSetRoute = document.getElementById("setRouteTeste");

function setRoute(){
	let adpList = document.querySelector(".adp-fullwidth");
	adpList.classList.add("noneImportant");
	buttonSetRoute.classList.add("noneImportant");

	buttonSetRoutePreviou.classList.add("noneImportant");

	let adp = document.querySelector(".adp");
	adp.classList.remove("noneImportant");
}

buttonSetRoute.addEventListener("click", setRoute);



let buttonSetRoutePreviou = document.getElementById("setRoutePreviou");

function setRoutePreviou(){
	let adpList = document.querySelector(".adp-fullwidth");
	adpList.classList.add("noneImportant");

	buttonSetRoutePreviou.classList.add("noneImportant");

	let adp = document.querySelector(".adp");
	adp.classList.remove("noneImportant");
}

buttonSetRoutePreviou.addEventListener("click", setRoutePreviou);