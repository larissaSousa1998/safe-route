function setEnderecos(partida, destino, card, nome) {

    containerInitial.classList.add("none");
    areaRouteGroup.classList.add("none");
    areaRouteGroupPath.classList.remove("none");

    setTimeout(function(){
        areaRouteGroupPath.classList.add("animate");
    },200);

    document.querySelector("h1#groupRouteTitle").innerHTML = nome;

    document.querySelector("div#areaCardGroup").innerHTML = card.innerHTML;

    montarRota(partida, destino, "grupo-locomocao");
};

function setRouteGroups(btn){
	let adpList = document.querySelector(".adp-fullwidth");
	adpList.classList.add("noneImportant");
	buttonSetRoute.classList.add("noneImportant");

	buttonSetRoutePreviou.classList.add("noneImportant");

	let adp = document.querySelector(".adp");
	adp.classList.remove("noneImportant");

    btn.classList.add("none");
}