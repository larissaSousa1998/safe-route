var geocoder;
let endereco;

function initialize() {
  geocoder = new google.maps.Geocoder();
}

function converterCoordernadas(lat, lng) {
  return new Promise(function(resolve, reject) {
    var latlng = new google.maps.LatLng(lat, lng);

    geocoder.geocode({
      'latLng': latlng
    }, function (results, status) {
      if (status === google.maps.GeocoderStatus.OK) {
        //document.getElementById("input-find-group").value = results[0].formatted_address;
        resolve(results[0].formatted_address);
      }
    });
  })
}

google.maps.event.addDomListener(window, 'load', initialize);