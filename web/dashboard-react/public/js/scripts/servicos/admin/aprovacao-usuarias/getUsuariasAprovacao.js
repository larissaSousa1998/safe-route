let usersAprove;

function getUsuariasAprovacao() {
    let xhr = new XMLHttpRequest();

    xhr.open("GET", `${BASE_URL}/usuarias/aprovacao`);

    xhr.onreadystatechange = async function () {
        if(xhr.readyState === 4 && xhr.status === 200) {
            let usuarias = JSON.parse(xhr.responseText);

            fillHtmlList(usuarias.fila);
            usersAprove = usuarias.fila;
            document.querySelector(".label-contas-pendentes").innerHTML = usuarias.fila.length;

        }
    }
    xhr.send();
}

getUsuariasAprovacao();


function fillHtmlList(usuarias) {

    let div = document.querySelector("div#contas-aprovacao-area");
    div.innerHTML = "";
    console.log(usuarias)

    usuarias.forEach(usuaria => {
        div.innerHTML += `
            <div class="area-contas" onClick='expandModalAprove(${JSON.stringify(usuaria)})'>
                <div class="contas-pendentes">
                    <div class="contas">
                        <div class="espaco-img img-user-aprove">
                            <div class="img2">
                                <img class="foto-usuaria-aprovacao" src="data:image/png;base64,${usuaria.selfie}">
                            </div>
                        </div>
                        <div class="informacao-usuaria">
                            <label id="name-usuaria">${usuaria.nome}</label>
                        </div>
                        <div class="email-usuaria">
                            <label id="email-user">${usuaria.email}</label>
                        </div>
                        <div class="idade-usuaria">
                            <label id="email-user">${calcAge(usuaria.dataNascimento)} anos</label>
                        </div>
                        <div class="documento-usuaria">
                            <label id="email-user">${usuaria.documento.tipo}: ${usuaria.documento.numeracao}</label>
                        </div>
                        <div class="lupa">
                            <span class="material-icons lup">
                                search
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        `;
    });
}


function calcAge(dataNascimento) {
    let dataNascimentoAsTimestamp = new Date(dataNascimento).getTime();
    let currentDateAsTimestamp = new Date().getTime();
    let ageAsTimestamp = currentDateAsTimestamp - dataNascimentoAsTimestamp;
    return Math.round(((ageAsTimestamp / 1000) / (60 * 60 * 24 * 365)));
}

let users = document.querySelectorAll('.area-contas');
modal = document.querySelector('.shadow-area-aprove');

function expandModalAprove(user){
    modal.classList.remove('noneImportant');

    div = document.querySelector('.modal-area-aprove');

        div.innerHTML = `
            <div class="row-document">
                <div class="document document-photo">
                    <label>Foto</label>
                    <div class="document-image document-image-photo">
                        <img class="foto-usuaria-aprovacao" src="data:image/png;base64,${user.selfie}">
                    </div>
                </div>
                <div class="document document-horizontal">
                    <label>Frente</label>
                    <div class="document-image document-image-horizontal">
                        <img class="foto-usuaria-aprovacao" src="data:image/png;base64,${user.documento.fotoFrente}">
                    </div>
                </div>
                <div class="document document-horizontal">
                    <label>Verso</label>
                    <div class="document-image document-image-horizontal">
                        <img class="foto-usuaria-aprovacao" src="data:image/png;base64,${user.documento.fotoVerso}">
                    </div>
                </div>
            </div>

            <div class="row-inputs row-inputs-aprove">
                <div class="input-area input-modal input-modal-aprove">
                    <label class="label-modal-aprove">Nome</label>
                    <input type="text" class="input-group modal-input disabled" value="${user.nome}">
                </div>
                <div class="input-area input-modal input-modal-aprove">
                    <label class="label-modal-aprove">Email</label>
                    <input type="text" class="input-group modal-input disabled" value="${user.email}">
                </div>
                <div class="input-area input-modal input-modal-aprove">
                    <label class="label-modal-aprove">Idade</label>
                    <input type="text" class="input-group modal-input disabled" value="${calcAge(user.dataNascimento)} anos">
                </div>
            </div>

            <div class="row-inputs row-one-button row-one-button-aprove">
                <input type="submit" class="btnCadGroup btnModalAprove btnModalAproveDanger" onClick="reproveUser(${user.id})" value="Rejeitar"/>
                <input type="submit" class="btnCadGroup btnModalAprove" value="Aprovar" onClick="aproveUser(${user.id})"/>
            </div>
        `;
}

function decreaseModalAprove(){
    modal.classList.add('noneImportant');
}

modal.addEventListener("click", decreaseModalAprove);


function aproveUser(id) {
    let xhr = new XMLHttpRequest();

    xhr.open("PUT", `${BASE_URL}/usuarias/aprovar/${id}`);

    xhr.onload = function () {
        if(xhr.readyState === 4 && xhr.status === 200) {
            alert('Usuária aprovada');
            preencherCards()
            getUsuariasAprovacao();
            fillHtmlList(usersAprove);
        }
    }
    xhr.send();
}

function reproveUser(id) {
    let xhr = new XMLHttpRequest();

    xhr.open("DELETE", `${BASE_URL}/usuarias/${id}`);

    xhr.onload = function () {
        if(xhr.readyState === 4 && xhr.status === 200) {
            alert('Usuária reprovada');
            preencherCards()
            getUsuariasAprovacao();
            fillHtmlList(usersAprove);
        }
    }
    xhr.send();
}