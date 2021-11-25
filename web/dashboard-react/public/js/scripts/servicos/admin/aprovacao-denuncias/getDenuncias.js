
let denuncias;

function getDenuncias() {

    let xhr = new XMLHttpRequest();

    xhr.open("GET", `${BASE_URL}/denuncias-local`);

    xhr.onreadystatechange = async function () {
        if(xhr.readyState === 4 && xhr.status === 200) {
            denuncias = JSON.parse(xhr.responseText);

            fillHtmlList(denuncias);
            document.querySelector(".label-total-denuncias").innerHTML = denuncias.length;
        } else if (xhr.status == 204){
            document.querySelector(".label-total-denuncias").innerHTML = 0;
        }
    }
    xhr.send();
}

getDenuncias();


function fillHtmlList(denuncias) {

    let div = document.querySelector("div.denuncias-aprovacao-area");
    div.innerHTML = "";

    denuncias.forEach(denuncia => {
        div.innerHTML += `
            <div class="area-contas" onClick='expandModalAprove(${JSON.stringify(denuncia)})'>
                <div class="contas-pendentes">
                    <div class="contas">
                        <div class="informacao-usuaria">
                            <label id="name-usuaria" class="denuncia-local-list">${denuncia.local}</label>
                        </div>
                        <div class="email-usuaria">
                            <label id="email-user">${denuncia.descricao}</label>
                        </div>
                        <div class="idade-usuaria">
                            <label id="email-user">${denuncia.usuaria.nome}</label>
                        </div>
                        <div class="documento-usuaria">
                            <label id="email-user">${denuncia.usuaria.email}</label>
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

modal = document.querySelector('.shadow-area-aprove-denuncia');

function expandModalAprove(denuncia){

    modal.classList.remove('noneImportant');
    div = document.querySelector('.modal-area-denuncia');

    openedDenuncia = denuncia;
    console.log(openedDenuncia);

    div.innerHTML = `
        <div class="header-modal">
            <h1>Detalhes da denúncia</h1>
        </div>
        <form action="">
            <div class="row-inputs">
                <div class="input-area input-modal">
                    <label>Local</label>
                    <input type="text" class="input-group modal-input" placeholder="${denuncia.local}">
                </div>
                <div class="input-area input-modal">
                    <label>Descrição</label>
                    <input type="text" class="input-group modal-input" placeholder="${denuncia.descricao}">
                </div>
            </div>
            <div class="row-inputs">
                <div class="input-area input-modal">
                    <label>Fonte</label>
                    <input type="text" class="input-group modal-input" placeholder="${
                        denuncia.fonte == null || denuncia.fonte == "" ? "Não informada" : denuncia.fonte}
                    ">
                </div>
                <div class="input-area input-modal">
                    <label>Feita por</label>
                    <input type="text" class="input-group modal-input" placeholder="${denuncia.usuaria.nome}">
                </div>
            </div>
            <div class="row-inputs">
                <div class="input-area input-modal">
                    <input type="submit" class="input-group modal-input btnDenuncia danger" onclick="deleteDenuncia('${denuncia.id}')" value="Rejeitar">
                </div>
                <div class="input-area input-modal">
                    <input type="button" class="input-group modal-input btnDenuncia success aprovarDenuncia" value="Aprovar" onclick="('')">
                </div>
            </div>
        </form> 
    `;

    document.querySelector('.aprovarDenuncia').addEventListener('click', convert);
}


// function decreaseModalAprove(){
//     modal.classList.add('noneImportant');
// }

// modal.addEventListener("click", decreaseModalAprove);


let openedDenuncia;

function convert(event){
    event.preventDefault();
    
    let promiseCoordenadas = converterEnderecoEmCoordernadas(openedDenuncia.local);

    promiseCoordenadas.then((result) => {
        postDenuncia(result);
    });

}

function postDenuncia(coordenadas) {
    const xhr = new XMLHttpRequest();
    xhr.open('POST',`${BASE_URL}/areas-risco`);
    
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function() {
        if (xhr.status === 201 && xhr.readyState === 4) {
            let response = xhr.responseText;
            alert("Denuncia aprovada com sucesso");
            deleteDenuncia(openedDenuncia.id);
        }
    }
    xhr.send(JSON.stringify(coordenadas));
};

function deleteDenuncia(id) {

    const xhr = new XMLHttpRequest();
    xhr.open('DELETE',`${BASE_URL}/denuncias-local/${id}`);
    
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function() {
        if (xhr.status === 200 && xhr.readyState === 4) {
            let response = xhr.responseText;
            window.location.reload();
        }
    }
    xhr.send();
};