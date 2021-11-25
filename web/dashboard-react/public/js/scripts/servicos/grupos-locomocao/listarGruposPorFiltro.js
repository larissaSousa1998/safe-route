function filtrarGrupos() {
    let promiseGrupos = getGruposLocomocaoUsuariaParticipa(idUsuaria);
    promiseGrupos.then(function(result) {
        setarEnderecosGrupos(result).then(result => {
           setTimeout(()=>{
            preencherHTML(getGruposUsuariaAdministra(result), "grupos-criados");
            preencherHTML(getGruposRecentes(result), "grupos-recentes");
            preencherHTML(result, "grupos-ativos");
           }, 500);
        })
    });
}

function setarEnderecosGrupos(grupos) {
    return new Promise(function(resolve, reject) {
        grupos.forEach(grupo => {
            let promiseOrigem = converterCoordernadas(grupo.viagem.latitudeOrigem, grupo.viagem.longitudeOrigem);
            let promiseDestino = converterCoordernadas(grupo.viagem.latitudeDestino, grupo.viagem.longitudeDestino);

            promiseOrigem.then(function (result){
                grupo.viagem.partida = formatarEndereco(result);
            });
            promiseDestino.then(function (result){ 
                grupo.viagem.destino =  formatarEndereco(result);
            });
        });

        resolve(grupos);
    })
}

filtrarGrupos();

//Grupos em que a usuaria é administradora

function getGruposUsuariaAdministra(grupos) {
    let gruposFiltrados = [];

    grupos.forEach(grupo => {
        let administradora = getAdmnistradoraGrupo(grupo.participantes);
        if(administradora.usuaria.id == idUsuaria) {
            gruposFiltrados.push(grupo);
        }
    });
    return gruposFiltrados;
}

function getAdmnistradoraGrupo(participantes) {
    for(i = 0; i < participantes.length; i++) {
        if(participantes[i].administradora) {
            return participantes[i];
        }
    }
}

//grupos em que a usuária entrou recentemente

function getGruposRecentes(grupos) {

    let gruposFiltrados = [];

    grupos.sort(ordenarPorData);

    for(i = 0; i < 5; i++) {
        if(grupos[i] != undefined) {
            gruposFiltrados.push(grupos[i]);
        } else {
            break;
        }
    }

    return gruposFiltrados;
}

function ordenarPorData(a, b) {
    for(i = 0; i < a.participantes.length; i++) {
        if(a.participantes[i].usuaria.id == idUsuaria && b.participantes[i].usuaria.id == idUsuaria) {
            return new Date(b.participantes[i].dataEntradaUsuaria).getTime() - 
                new Date(a.participantes[i].dataEntradaUsuaria).getTime();       
        }
    }
}