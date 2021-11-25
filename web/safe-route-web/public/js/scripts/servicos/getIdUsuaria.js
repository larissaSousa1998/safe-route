
var idUsuaria;

function getIdUsuaria(){
    let dadosUsuaria = JSON.parse(sessionStorage.getItem("usuaria"));
    idUsuaria = dadosUsuaria.id;
}

getIdUsuaria();