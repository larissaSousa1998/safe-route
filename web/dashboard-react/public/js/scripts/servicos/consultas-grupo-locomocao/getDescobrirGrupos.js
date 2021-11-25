function getLocalizacao(){
    let dadosLocalizacao = JSON.parse(sessionStorage.getItem("localizacao"));
    lat = dadosLocalizacao.latitude;
    long = dadosLocalizacao.longitude;

    getGruposDescobrir(`${BASE_URL}/viagens`, lat, long);
}

getLocalizacao();

function getGruposDescobrir(url, latitude, longitude) {
    if(latitude != undefined && latitude != "" && longitude != undefined && longitude != ""){
        let xhr = new XMLHttpRequest();
        console.log(latitude);
        console.log(longitude);
        xhr.open("GET", `${url}/pegar-distancia?latitude=${latitude}&longitude=${longitude}&id=${idUsuaria}`);
    
        xhr.onreadystatechange = function() {
            if(xhr.readyState === 4 && xhr.status === 200) {
                let gruposNovos = JSON.parse(xhr.responseText);
                
                gruposNovos.forEach(grupo => {
                    getDadosAdmin(grupo);
                    //setViagemGrupo(grupo);
                    //setAdministradoraGrupo(grupo);
                    //console.log(converterCoordernadas(grupo.latitudeOrigem, grupo.longitudeOrigem));
                    grupo["origem"] = "";
                    grupo["destino"] = "";
                    grupo["administradora"] = "";
                    converterCoordernadas(grupo.latitudeOrigem, grupo.longitudeOrigem)
                    .then((dados) => {
                        grupo.origem = dados;
                    });

                    converterCoordernadas(grupo.latitudeDestino, grupo.longitudeDestino)
                    .then((dados) => {
                        grupo.destino= dados;
                    })
                });
                
                console.log(gruposNovos);
                setTimeout(() => {
                    fillHtmlList("grupos-descobrir", gruposNovos);
                }, 500);
            }
        }
    
        xhr.send();

    }else {
        alert("Ative a sua localização")
    }

}

function getDadosAdmin(grupo){
    let xhr = new XMLHttpRequest();
    xhr.open("GET", `${BASE_URL}/administradores/grupo?id=${grupo.id}`);
    xhr.onreadystatechange = function () {
        if(xhr.status === 200 && xhr.readyState === 4) {
            let obj = JSON.parse(xhr.responseText);
            grupo.administradora = obj;
        }
    }
    xhr.send();
} 

function setAdministradoraGrupo(grupo) {

    let xhr = new XMLHttpRequest();

    xhr.open("GET", `${BASE_URL}/usuarias/${grupo.idUsuaria}`);

    xhr.onreadystatechange = function () {
        if(xhr.status === 200 && xhr.readyState === 4) {
            grupo.administradora = JSON.parse(xhr.responseText);
        }
    }

    xhr.send();
}

function setViagemGrupo(grupo) {
    let xhr = new XMLHttpRequest();

    xhr.open("GET", `${BASE_URL}/viagens/${grupo.idViagem}`);

    xhr.onreadystatechange = function () {
        if(xhr.status === 200 && xhr.readyState === 4) {
            grupo.viagem = JSON.parse(xhr.responseText);
            // setDataViagem(grupo.viagem);
        }
    }

    xhr.send();
}

function fillHtmlList(idHTML, grupos) {

    let div = document.querySelector(`div#${idHTML}-area`);

    div.innerHTML = "";

    grupos.forEach(grupo => {
        document.querySelector("img.photo-adm").src = "data:image/png;base64,"+grupo.administradora.foto;
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
                            <img class="photo-adm" src="">
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
    console.log(grupo);
    let idCard = "div#card-modal";

    let h1GroupName = document.querySelector(` h1.group-name`);

    h1GroupName.innerHTML = grupo.nome;

    let h1Data = document.querySelector(`${idCard} h1.label-data`);

    h1Data.innerHTML = formataDataPadrao(grupo.dataCriacaoGrupo);

    let h1Origin = document.querySelector(`${idCard} h1.label-origin`);

    h1Origin.innerHTML = grupo.origem;

    let h1End = document.querySelector(`${idCard} h1.label-end`);

    h1End.innerHTML = grupo.destino;

    let h1Adm = document.querySelector(`${idCard} h1.label-adm`);

    h1Adm.innerHTML = grupo.administradora.nome;

    let imgAdms = document.querySelectorAll(`${idCard} img.photo-adm`);

    imgAdms.forEach(imgAdm => {
        imgAdm.src= "data:image/png;base64,"+grupo.administradora.foto;
    });

    let member1 = document.querySelector(`${idCard} h1.name-card-member`);

    member1.innerHTML = grupo.administradora.nome;

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
