containerInitial = document.querySelector(".define-initial");
containerOptionsRoute = document.querySelector(".options-route");
containerPath = document.querySelector(".routes-path");

function choiceRoute(e){
    containerInitial.classList.add("none");  
    containerOptionsRoute.classList.remove("none");

    document.getElementById("input-destination").value

    setTimeout(function(){
        containerOptionsRoute.classList.add("animate");
    },200)
}

setTimeout(function(){
    cardChoice = document.querySelectorAll(".choice-route");

    cardChoice.forEach(card => {
        card.addEventListener("click", choiceRoute);
        card.addEventListener("click", getLocalizacoes);
    });
},200)



// --------------------------------------------------

let cardDestinationSuggestion = document.querySelectorAll(".card-destination-suggestion");
let button = document.querySelectorAll(".btn-defined-destination");

function setRoute(e){
    cardDestinationSuggestion.forEach(card => {
        card.classList.remove("add-color-card");
    });

    card = e.target;
    id = card.getAttribute("id");
    if(e.target.id == id){
        card.classList.add("add-color-card");
    }

    button.forEach(btn => {
        btn.classList.add("add-color-btn");
    });
}

cardDestinationSuggestion.forEach(card => {
    card.addEventListener("click", setRoute);
});

// --------------------------------------------------

let buttonDefined = document.querySelectorAll(".btn-defined-destination");
let areaRouteGroup = document.querySelector(".route-group");
let areaRouteGroupPath = document.querySelector(".route-path-group");

function defined(e){
    if(e.target.classList.contains("add-color-btn")){

        if(e.target.id == "setRoutePreivou"){
            containerOptionsRoute.classList.add("none");
            containerPath.classList.remove("none");

            setTimeout(function(){
                containerPath.classList.add("animate");
            },200)

        } if(e.target.id == "setRouteGroup"){
            areaRouteGroup.classList.add("none");
            areaRouteGroupPath.classList.remove("none");

            setTimeout(function(){
                areaRouteGroupPath.classList.add("animate");
            },200)

        }
    } 
}

buttonDefined.forEach(btn => {
    btn.addEventListener("click", defined);
});



// ----------------------------

let cardGroupLocomotion = document.querySelectorAll(".setRouteGroup");

function setRouteGroup(){
    containerInitial.classList.add("none");  
    areaRouteGroup.classList.remove("none");

    setTimeout(function(){
        areaRouteGroup.classList.add("animate");
    },200)

}

cardGroupLocomotion.forEach(card => {
    card.addEventListener("click", setRouteGroup);
});

