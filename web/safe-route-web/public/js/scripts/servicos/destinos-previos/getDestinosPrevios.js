var destinosPrevios;

function getDestinosPrevios() {
    destinosPrevios = JSON.parse(sessionStorage.getItem("usuaria")).enderecos;

    let previous = document.querySelector(".previous");
    
    destinosPrevios.forEach(destino => {
        previous.innerHTML += `
        <div class="card-destination choice-route">
            <div class="text">
                <h4>${destino.descricao}</h4>
                <label class="label-destination">${destino.logradouro}, ${destino.numero}</label>
            </div>
            <div class="icon-walk">
                <span class="material-icons">
                    directions_walk
                </span>
            </div>
        </div>
        `;
    });
}

getDestinosPrevios();