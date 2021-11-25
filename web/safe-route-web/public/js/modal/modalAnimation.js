let createGroup = document.querySelector('#createGroup');
let modal = document.querySelector('.shadow-area');

function expandModal(){
    modal.classList.remove('noneImportant');
}
createGroup.addEventListener('click', expandModal);


// function toHideModal(){
//     modal.classList.add('noneImportant');
// }
// modal.addEventListener('click', toHideModal);

function decrease(){
    modal.classList.add('noneImportant');
}
let closed = document.querySelector('.icon-close');
closed.addEventListener('click', decrease);


// --------------------------


function goToDescobrirGrupos(){
    window.location.assign("descobrirGrupos.html");
}
document.getElementById('descobrirGrupos').addEventListener('click', goToDescobrirGrupos);