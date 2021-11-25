
let usuariaData = JSON.parse(sessionStorage.getItem('usuaria'));

function printHeader(){
    let div = document.querySelector("div.create-publication");
    div.innerHTML = "";

    div.innerHTML = `
        <div class="user-identification create-user-pub">
            <div class="create-title-pub">
                <div class="user-identification-img">
                    <img src="data:image/png;base64,${usuariaData.foto}">
                </div>
                <div class="user-identification-name">
                    <label class="identification-local">Compartilhe algo com outras usuárias :)</label>
                </div>
            </div>
            <button class="expandedModalCreate createPublication">Nova publicação</button>
        </div>
    `;
}

console.log(usuariaData);
printHeader();








let publicacoes;

function getPublicacoes() {

    let xhr = new XMLHttpRequest();

    xhr.open("GET", `${BASE_URL}/publicacoes`);

    xhr.onreadystatechange = async function () {
        if(xhr.readyState === 4 && xhr.status === 200) {
            publicacoes = JSON.parse(xhr.responseText);
            fillHtmlList(publicacoes);
            console.log(publicacoes)
        }
    }
    xhr.send();
}

getPublicacoes();


function fillHtmlList(publicacoes) {

    let div = document.querySelector("div.select-publicacoes");
    div.innerHTML = "";

    publicacoes.pilha.forEach(publicacao => {
        div.innerHTML += `
            <div class="publication">
                <div class="user-identification">
                    <div class="user-identification-img"> 
                        <img src="data:image/png;base64,${publicacao.usuaria.foto}" class="publication-src img-user-pub">
                    </div>
                    <div class="user-identification-name">
                        <label class="identification-name">${publicacao.usuaria.nome}</label>
                        <label class="identification-local">from São Paulo</label>
                    </div>
                </div>
                <div class="publication-area-image">
                    <div class="publication-image">
                        <img src="data:image/png;base64,${publicacao.foto}" class="publication-src">
                    </div>
                </div>
                <div class="publication-text">
                    <h1>${publicacao.titulo}</h1>
                    <label>
                        ${publicacao.descricao}
                    </label>
                </div>
                <div class="publications-tags">
                    <div class="publication-tag">
                        <label>#Denúncia</label>
                    </div>
                    <div class="publication-tag">
                        <label>#SP</label>
                    </div>
                    <div class="publication-tag">
                        <label>#Ajuda</label>
                    </div>
                    <div class="publication-tag">
                        <label>#JovenPan</label>
                    </div>
                    <div class="publication-tag">
                        <label>#SP</label>
                    </div>
                    <div class="publication-tag">
                        <label>#Ajuda</label>
                    </div>
                </div>

                <div class="publication-insigths">
                    <div class="insigths">

                        <div class="item item-insigths">
                            <span class="material-icons-outlined icon-insigths">
                                favorite_border
                            </span>
                            <label>${publicacao.curtidas} Curtidas</label>
                        </div>

                        <div class="item item-insigths">
                            <span class="material-icons-outlined icon-insigths">
                                visibility
                            </span>
                            <label>150 Visualizações</label>
                        </div>

                    </div>
                    <div class="insigths">
                        <div class="item item-insigths item-insigths-margin">
                            <span class="material-icons-outlined icon-insigths">
                                east
                            </span>
                            <label>Compartilhar</label>
                        </div>
                    </div>
                </div>
            </div>
        `;
    });
}