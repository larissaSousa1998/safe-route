function atualizarInformacoesDoInput(input) {
    let label = input.previousElementSibling;

    label.innerHTML = formatarNomeArquivo(input.value);

    let icon = label.previousElementSibling;

    icon.classList.remove("fa-upload");
    icon.classList.add("fa-check");
}

function formatarNomeArquivo(caminhoArquivo) {

    let caminhoInvertido = 
        caminhoArquivo
            .split("")
            .reverse()
            .join("");

    let nomeArquivo = 
        caminhoInvertido
            .substring(0, caminhoInvertido.indexOf("\\"))
            .split("")
            .reverse()
            .join("");

    return nomeArquivo;

}