function preencherHTML(grupos, idHTML) {

    let span = document.querySelector(`span#${idHTML}-total`);
    span.innerHTML = grupos.length;

    let div = document.querySelector(`div#${idHTML}-area`);

    div.innerHTML = "";

    grupos.forEach(grupo => {

        grupo.participantes = organizarParticipantes(grupo.participantes);
        
        div.innerHTML += `
        <div class="card-destination group-destination new-card card-goups-user" id="grupo${grupo.id}" onclick='expandCardGroupUser();preencherDadosCard(${JSON.stringify(grupo)})'>
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
                <h1 class="card-title-h1 card-title-h1-small">${formatarData(grupo.viagem.datasViagem[0])}</h1>
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
                <h1 class="card-title-h1 card-title-h1-small h1-start">${grupo.viagem.partida}</h1>
            </div>
            <div class="text-group">
                <label class="card-title-label">Destino</label>
                <h1 class="card-title-h1 card-title-h1-small h1-end">${grupo.viagem.destino}</h1>
            </div>
        </div>
    </div>

    <div class="card-footer">
        <div class="box box-footer">
            <div class="user-destination">
                <div class="user-photo-destination">
                    <img class="photo-adm" src="data:image/png;base64,${grupo.participantes.administradora.usuaria.foto}">
                </div>
                <div class="text-group">
                    <label class="card-title-label">Administradora</label>
                    <h1 class="card-title-h1 card-title-h1-small">${grupo.participantes.administradora.usuaria.nome}</h1>
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

function formatarEndereco(endereco){
    let index = endereco.indexOf('-');
    return endereco.substring(0,index);
}

function formatarData(data) {
    //TO DO: Dado um vetor de datas, verificar se é o possivel ver a data 
    //mais proxima a partir da data atual, e exibir no formato:
    // Próxima data
    // 23 Setembro 2020 21:30 hrs

    let diaSemana;

    switch(data.diaSemana) {
        case "SEG":
            diaSemana = "Segunda-Feira";
            break;
        case "TER":
            diaSemana = "Terça-Feira";
            break;
        case "QUA":
            diaSemana = "Quarta-Feira";
            break;
        case "QUI":
            diaSemana = "Quinta-Feira";
            break;
        case "SEX":
            diaSemana = "Sexta-Feira";
            break;
        case "SAB":
            diaSemana = "Sábado";
            break;
        case "DOM":
            diaSemana = "Domingo";
            break;
    }

    let horario = data.horario.split(":");
    horario = `${horario[0]}:${horario[1]} hrs`;

    return `${diaSemana}, ${horario}`;
}

function organizarParticipantes(participantes) {
    
    for(i = 0; i < participantes.length; i++) {
        if(participantes[i].administradora) {
            participantes.administradora = participantes[i];
        }
    }

    return participantes;
}

function preencherDadosCard(grupo) {

    let idCard = "div#card-modal";

    let h1Data = document.querySelector(`${idCard} h1.data-grupo`);
    h1Data.innerHTML = formatarData(grupo.viagem.datasViagem[0]);

    let h1GroupName = document.querySelector(`${idCard} h1.group-name`);

    h1GroupName.innerHTML = grupo.nome;

    let h1Origin = document.querySelector(`${idCard} h1.label-origin`);

    h1Origin.innerHTML = grupo.viagem.partida;

    let h1End = document.querySelector(`${idCard} h1.label-end`);

    h1End.innerHTML = grupo.viagem.destino;

    grupo.participantes = organizarParticipantes(grupo.participantes);

    console.log(grupo);

    let h1Adm = document.querySelector(`${idCard} h1.label-adm`);

    h1Adm.innerHTML = grupo.participantes.administradora.usuaria.nome;

    let imgAdm = document.querySelector(`${idCard} img.photo-adm`);

    imgAdm.src= "data:image/png;base64,"+grupo.participantes.administradora.usuaria.foto;

    let membersArea = document.querySelector(`${idCard} .modal-list-members`);

    let participantes = grupo.participantes;

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
}