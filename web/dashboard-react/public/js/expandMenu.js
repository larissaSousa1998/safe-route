let expand = document.querySelector(".expand");
let sidemenu = document.querySelector(".side-menu-area");

function expandMenu(){
    sidemenu.classList.toggle("none");
}

expand.addEventListener("click", expandMenu);
sidemenu.addEventListener("click", expandMenu);
