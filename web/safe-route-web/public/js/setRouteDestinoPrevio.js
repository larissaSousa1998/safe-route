
function previouRoute(){
    setTimeout(() => {
        if(!document.querySelector('.adp-list')){
            document.querySelector('.area-btn-defined-previou').classList.add('noneImportant');
            setTimeout(() => {
                document.querySelector('.adp').classList.remove('noneImportant');
            }, 2000);
        }  
    }, 500);
}