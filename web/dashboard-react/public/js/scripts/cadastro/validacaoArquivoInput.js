function validarArquivo(input, tamanho) {
    let validacoes = 2;

    var extPermitidas = ['jpg', 'png', 'jpeg'];
    var extArquivo = input.value.split('.').pop();

    let labelErro = input.nextElementSibling;

    if(typeof extPermitidas.find(function(ext) { 
        return extArquivo == ext; }) == 'undefined') {
        labelErro.innerHTML = ('Extensão "' + extArquivo + '" não permitida!');
        clearFileInput(document.getElementById(input.getAttribute("id")));
        validacoes--;
    }

    if(((input.files[0].size / 1024) /1024) > tamanho) {
        labelErro.innerHTML = ("O arquivo deve possuir menos de " + tamanho * 1000 + "KB");
        clearFileInput(document.getElementById(input.getAttribute("id")));
        validacoes--;
    }

    if(validacoes == 2) {
        atualizarInformacoesDoInput(input);
        labelErro.innerHTML = "";
    }

    return validacoes;
}

function clearFileInput(ctrl) {
    try {
        ctrl.value = null;
    } catch(ex) { }
    if (ctrl.value) {
        ctrl.parentNode.replaceChild(ctrl.cloneNode(true), ctrl);
    }
}