async function exec() {
    let dadosLocalizacao = JSON.parse(sessionStorage.getItem("localizacao"));
    lat = dadosLocalizacao.latitude;
    long = dadosLocalizacao.longitude;

    let grupos = await getGrupos(`${BASE_URL}/viagens`, lat, long);
    let gruposFormatados = await setEndereco(grupos);
    let gruposComParticipantes = await getParticipantesGrupos(gruposFormatados);
    fillHtmlList("grupos-descobrir", gruposComParticipantes);
}

exec();

async function organizarParticipantes(grupo) {
    for(let i = 0; i < grupo.participantes.length; i++) {
        if(grupo.participantes[i].administradora) {
            grupo.administradora = grupo.participantes[i].usuaria;
            grupo.participantes.splice(i, 1);
        }
    }
    return grupo;
}

async function getParticipantesGrupos(grupos) {
    for(grupo of grupos) {
        let xhr = new XMLHttpRequest();
        xhr.open("GET", `${BASE_URL}/grupos-locomocao/${grupo.id}`);
    
        xhr.onreadystatechange = async function() {
            if(xhr.readyState === 4 && xhr.status === 200) {
                let dadosGrupo = JSON.parse(xhr.responseText);
                grupo.participantes = dadosGrupo.participantes;
                grupo = await organizarParticipantes(grupo);
                setTimeout(() => {
                    // fillHtmlList("grupos-descobrir", gruposNovos);
                }, 500);
            }
        }
    
        xhr.send();

        await sleep(1000);
    }

    return grupos;
}

async function getGrupos(url, latitude, longitude) {
    return new Promise((resolve, reject) => {
        if(latitude != undefined && latitude != "" && longitude != undefined && longitude != ""){
            let xhr = new XMLHttpRequest();
            xhr.open("GET", `${url}/pegar-distancia?latitude=${latitude}&longitude=${longitude}&id=${idUsuaria}`);
        
            xhr.onreadystatechange = function() {
                if(xhr.readyState === 4 && xhr.status === 200) {
                    let gruposNovos = JSON.parse(xhr.responseText);
                    resolve(gruposNovos);
                }
            }
        
            xhr.send();
    
        }else {
            alert("Ative a sua localização")
        }
    });
}

function formatarEndereco(endereco){
    let index = endereco.indexOf('-');
    return endereco.substring(0,index);
}

async function setEndereco(grupos) {
    for(grupo of grupos) {
        let origem = await converterCoordernadas(grupo.latitudeOrigem, grupo.longitudeOrigem);
        let destino = await converterCoordernadas(grupo.latitudeDestino, grupo.longitudeDestino);

        grupo.origem = formatarEndereco(origem); 
        grupo.destino =  formatarEndereco(destino);

        // await sleep(2000);
    }

    return grupos;
}

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}













function fillHtmlList(idHTML, grupos) {

    let div = document.querySelector(`div#${idHTML}-area`);

    div.innerHTML = "";

    grupos.forEach(grupo => {
        div.innerHTML += `
        <div class="card-destination group-destination new-card card-goups-user cardNewGroup" onclick='expandCardGroup();fillCardInfo(${JSON.stringify(grupo)})'>
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
                        <h1 class="card-title-h1 card-title-h1-small">${formataDataPadrao(grupo.dataCriacaoGrupo)}</h1>
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
                        <label class="card-title-label">Ponto de partida
                        <h5 class="card-title-h5">${grupo.origem}</h1>
                        </label>
                        <h1 class="card-title-h1 card-title-h1-small h1-start"></h1>
                    </div>
                    <div class="text-group">
                        <label class="card-title-label">Destino
                        <h5 class="card-title-h5">${grupo.destino}</h1>
                        </label>
                        <h1 class="card-title-h1 card-title-h1-small h1-end"></h1>
                    </div>
                </div>
            </div>

            <div class="card-footer">
                <div class="box box-footer">
                    <div class="user-destination">
                        <div class="user-photo-destination">
                            <img class="photo-adm" src="data:image/png;base64,${grupo.administradora.foto}">
                        </div>
                        <div class="text-group">
                            <label class="card-title-label">Administradora</label>
                            <h1 class="card-title-h1">${grupo.administradora.nome}</h1>
                            <h1 class="card-title-h1 card-title-h1-small"></h1>
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
    });
}

function fillCardInfo(grupo) {
    let idCard = "div#card-modal";

    let h1GroupName = document.querySelector(`${idCard} h1.group-name`);
    let h1Data = document.querySelector(`${idCard} h1.data-grupo`);
    h1Data.innerHTML = formataDataPadrao(grupo.dataCriacaoGrupo);

    h1GroupName.innerHTML = grupo.nome;

    let h1Origin = document.querySelector(`${idCard} h1.label-origin`);

    h1Origin.innerHTML = grupo.origem;

    let h1End = document.querySelector(`${idCard} h1.label-end`);

    h1End.innerHTML = grupo.origem;

    let h1Adm = document.querySelector(`${idCard} h1.label-adm`);

    h1Adm.innerHTML = grupo.administradora.nome;

    let imgAdm = document.querySelector(`${idCard} img.photo-adm`);

    imgAdm.src= "data:image/png;base64,"+grupo.administradora.foto;

    let membersArea = document.querySelector(`${idCard} .modal-list-members`);

    let aux =  grupo.administradora;
    let participantes = [];
    participantes = grupo.participantes;
    participantes.splice(0, 0, {usuaria: aux});

    membersArea.innerHTML = "";

    participantes.forEach(participante => {
        membersArea.innerHTML += `
            <div class="card-member-modal orange-ligth">
                <div class="photo-member">
                    <img src='data:image/png;base64,${participante.usuaria.foto}'>
                </div>
                <h1 class="card-title-h1 card-title-h1-small name-card-member">${participante.usuaria.nome}</h1>
            </div>
        ` 
    });

    let spanMembers = document.querySelector(`${idCard} .members-count`);
    spanMembers.innerHTML = grupo.participantes.length;

    document.querySelector(`${idCard} #createGroup`).addEventListener("click", function() {
        entrarNoGrupo(grupo.id, idUsuaria);
    });
    
}

function entrarNoGrupo(idGrupo, idUsuaria) {
    let grupoLocomocaoEntrar = {
        "idUsuaria": idUsuaria,
        "idGrupoLocomocao": idGrupo,
        "dataEntrada": formataData(new Date())
    }

    let xhr = new XMLHttpRequest();

    xhr.open("POST", `${BASE_URL}/grupos-entrar`);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function() {
        if(xhr.status === 200 && xhr.readyState === 4) {
            alert("Você entrou no grupo!");
            window.location = "grupoLocomocao.html";
        }
    }

    xhr.send(JSON.stringify(grupoLocomocaoEntrar));

}

function formataData(dataGrupo) {
    let data = new Date(dataGrupo);
    let dia = data.getDate();
    let mes = data.getMonth() + 1;
    let ano = data.getFullYear();

    return `${ano}-${mes < 10 ? "0" + mes : mes}-${dia < 10 ? "0" + dia : dia}`;
}

function formataDataPadrao(dataGrupo){
    if(dataGrupo != null){
        const meses = ["Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul","Ago","Set","Out","Nov","Dez"];
        let data = new Date(dataGrupo);
        return ((data.getDate() + " " + meses[(data.getMonth())] + " " + data.getFullYear()));
    }else {
        return "-";
    }

}
