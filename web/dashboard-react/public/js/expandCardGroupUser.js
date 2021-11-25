
let modalCardUser = document.querySelector('.modal-card-user');

function expandCardGroupUser(){
    modalCardUser.classList.remove('noneImportant');
}

cardsUser = document.querySelectorAll('.card-goups-user');
cardsUser.forEach(card => {
    card.addEventListener('click', expandCardGroupUser)
});


function decreaseCardGroupUser(){
    modalCardUser.classList.add('noneImportant');
}

modalCardUser.addEventListener('click', decreaseCardGroupUser);