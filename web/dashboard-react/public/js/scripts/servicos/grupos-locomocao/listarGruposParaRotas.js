function listarGruposParaRotas() {
    let promiseGrupos = getGruposLocomocaoUsuariaParticipa(idUsuaria);
    promiseGrupos.then(function(result) {
         fillHtmlList(result);
    });
    
}

listarGruposParaRotas();

function fillHtmlList(grupos) {

    let div = document.querySelector("div#groups-list");

    div.innerHTML = "";

    grupos.forEach(grupo => {
        
        let administradora = getAdministradora(grupo);
        let partida = {
            "latitude": grupo.viagem.latitudeOrigem,
            "longitude": grupo.viagem.longitudeOrigem
        };

        let destino = {
            "latitude": grupo.viagem.latitudeDestino,
            "longitude": grupo.viagem.longitudeDestino
        };

        let promiseOrigem = converterCoordernadas(grupo.viagem.latitudeOrigem, grupo.viagem.longitudeOrigem);
        let promiseDestino = converterCoordernadas(grupo.viagem.latitudeDestino, grupo.viagem.longitudeDestino);

        div.innerHTML += `
        <div class="card-destination group-destination new-card card-goups-user" id="grupo${grupo.id}" onclick='setEnderecos(${JSON.stringify(partida)}, ${JSON.stringify(destino)}, this, "${grupo.nome}")'>
        <div class="card-header">
                <div class="box">
                    <div class="text-group">
                        <label class="card-title-label">Nome do grupo</label>
                        <h1 class="card-title-h1">${grupo.nome}</h1>
                    </div>
                    <div class="box-icons">
                        <div class="box-icon">
                            <span class="material-icons icon-date icon-in-box color-purple">
                                location_on
                            </span>
                        </div>
                        <div class="box-icon color-green">
                            <span class="material-icons icon-date icon-in-box color-green">
                                event_available
                            </span>
                        </div>
                    </div>
                </div>

                <div class="box sub-box">
                    <div class="text-group">
                        <label class="card-title-label">Próxima data</label>
                        <h1 class="card-title-h1 card-title-h1-small">23 Setembro 2020 21:30 hrs</h1>
                    </div>
                </div>
            </div>

            <div class="destination-card">
                <div class="icons-destination">
                    <div class="circle-destination"></div>
                    <div class="box-line">
                        <div class="line-destination"></div>
                    </div>
                    <div class="square-destination"></div>
                </div>
                <div class="box sub-box sub-box-destination">
                    <div class="text-group">
                        <label class="card-title-label">Ponto de saída</label>
                        <h1 class="card-title-h1 card-title-h1-small h1-start">Origem</h1>
                    </div>
                    <div class="text-group">
                        <label class="card-title-label">Destino</label>
                        <h1 class="card-title-h1 card-title-h1-small h1-end">Destino</h1>
                    </div>
                </div>
            </div>

            <div class="card-footer">
                <div class="box box-footer">
                    <div class="user-destination">
                        <div class="user-photo-destination">
                            <img class="photo-adm" src="data:image/png;base64,${administradora.foto}">
                        </div>
                        <div class="text-group">
                            <label class="card-title-label">Administradora</label>
                            <h1 class="card-title-h1 card-title-h1-small">${administradora.nome}</h1>
                        </div>
                    </div>
                    <div class="box-icons">
                        <div class="box-icon box-icon-ligth">
                            <span class="material-icons icon-date icon-in-box">
                                chat
                            </span>
                        </div>
                        <div class="box-icon box-icon-ligth">
                            <span class="material-icons icon-date icon-in-box">
                                arrow_forward
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    `;

    promiseOrigem.then(function (result){
        document.querySelector(`div#grupo${grupo.id} h1.h1-start`).innerHTML = formatarEndereco(result);
    })
    promiseDestino.then(function (result){
        document.querySelector(`div#grupo${grupo.id} h1.h1-end`).innerHTML = formatarEndereco(result);
    })
    });
}

function formatarEndereco(endereco){
    let index = endereco.indexOf('-');
    return endereco.substring(0,index);
}


function getAdministradora(grupo) {

    let participantes = grupo.participantes;

    for (let participante of participantes){
        
        if(participante.administradora) {
            return participante.usuaria;
        } 
    }

}