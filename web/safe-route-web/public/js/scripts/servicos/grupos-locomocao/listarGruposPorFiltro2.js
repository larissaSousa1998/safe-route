let gruposProntos= [];

setInterval(() => {
    if(gruposProntos.length > 0) {
        preencherHTML(getGruposUsuariaAdministra(gruposProntos), "grupos-criados");
        preencherHTML(getGruposRecentes(gruposProntos), "grupos-recentes");
        preencherHTML(gruposProntos, "grupos-ativos");
    }
}, 1000);

async function exec() {
    let grupos = await getAllGrupos();
    let arrays = await dividirGruposEmArrays(grupos);

    for(array of arrays) {
        let finalizou = await setEnderecoGrupos(array);
        if(finalizou) {
            gruposProntos.push(array[0]);
            if(array.length == 2) {
                gruposProntos.push(array[1]);
            }
        }
    }
}

exec();
 
async function getAllGrupos() {
    return await getGruposLocomocaoUsuariaParticipa(idUsuaria);
}

async function dividirGruposEmArrays(grupos) {
    let gruposArrays = [];
    for (let i = 0; i < grupos.length; i+=2) {
        let aux = [];
        aux.push(grupos[i]);
        
        if((i + 1) < grupos.length) {
            aux.push(grupos[i + 1]);
        }

        gruposArrays.push(aux);
    }
    return gruposArrays;
}

async function setEnderecoGrupos(grupos) {
    for(grupo of grupos) {
        let origem = await converterCoordernadas(grupo.viagem.latitudeOrigem, grupo.viagem.longitudeOrigem);
        let destino = await converterCoordernadas(grupo.viagem.latitudeDestino, grupo.viagem.longitudeDestino);

        grupo.viagem.partida = formatarEndereco(origem); 
        grupo.viagem.destino =  formatarEndereco(destino);

        await sleep(2500);
    }

    return true;
}

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

  // //Grupos em que a usuaria é administradora

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

    for(i = 0; i < 3; i++) {
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