// function filtrarGrupos() {
//     let promiseGrupos = getGruposLocomocaoUsuariaParticipa(idUsuaria);
//     promiseGrupos.then(function(result) {
//         setarEnderecosGrupos(result).then(result => {
//            setTimeout(()=>{
//             preencherHTML(getGruposUsuariaAdministra(result), "grupos-criados");
//             preencherHTML(getGruposRecentes(result), "grupos-recentes");
//             preencherHTML(result, "grupos-ativos");
//            }, 500);
//         })
//     });
// }

// function setarEnderecosGrupos(grupos) {
//     return new Promise(function(resolve, reject) {
//         grupos.forEach(grupo => {
//             let promiseOrigem = converterCoordernadas(grupo.viagem.latitudeOrigem, grupo.viagem.longitudeOrigem);
//             let promiseDestino = converterCoordernadas(grupo.viagem.latitudeDestino, grupo.viagem.longitudeDestino);

//             promiseOrigem.then(function (result){
//                 grupo.viagem.partida = formatarEndereco(result);
//             });
//             promiseDestino.then(function (result){ 
//                 grupo.viagem.destino =  formatarEndereco(result);
//             });
//         });

//         resolve(grupos);
//     })
// }

// filtrarGrupos();