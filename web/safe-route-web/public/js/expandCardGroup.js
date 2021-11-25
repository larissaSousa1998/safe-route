cards = document.querySelectorAll('.card-find-group');

function expandCardGroup(){
    modalCard = document.querySelector('.shadow-area-modal-card');
    modalCard.classList.remove('noneImportant');
}

cards.forEach(card => {
    card.addEventListener("click", expandCardGroup);
});



function backToGroups(){
    window.location.assign("grupoLocomocao.html");
}

document.getElementById('backToGroups').addEventListener('click', backToGroups);






