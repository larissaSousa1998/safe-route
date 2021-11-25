
let modalCreatePublication = document.querySelector('.modal-create-publication');
function expandModal(){
    modalCreatePublication.classList.remove('noneImportant');
}

expand = document.querySelectorAll('.expandedModalCreate');
expand.forEach(e => {
    e.addEventListener('click', expandModal)
});


function decrease(){
    modalCreatePublication.classList.add('noneImportant');
}
let closed = document.querySelector('.icon-close');
closed.addEventListener('click', decrease);


