var geocoder;

function initialize() {
    geocoder = new google.maps.Geocoder();
}
  
function converterEnderecoEmCoordernadas(endereco){
    initialize();
    return new Promise(function(resolve, reject) {
        geocoder.geocode({ 'address': endereco + ', Brasil', 'region': 'BR' }, function (results, status) {
            if (status === google.maps.GeocoderStatus.OK) {
                resolve({
                    "latitude": results[0].geometry.location.lat(),
                    "longitude": results[0].geometry.location.lng()
                });
            }   else {
                reject("");
            }
        });
    });
}