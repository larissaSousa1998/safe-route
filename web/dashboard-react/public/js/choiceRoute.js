let previousDestination = document.querySelector(".previous-destination");
let destinations = document.querySelector(".previous");

let groupDestination = document.querySelector(".group-route-option");
let groupLocomotion = document.querySelector(".group-locomotion");

function expandPrevious(){
    destinations.classList.toggle("none");
    groupLocomotion.classList.add("none");
}

previousDestination.addEventListener("click", expandPrevious);


function expandGroupLocomotion(){
    groupLocomotion.classList.toggle("none");
    destinations.classList.add("none");
}

groupDestination.addEventListener("click", expandGroupLocomotion);




