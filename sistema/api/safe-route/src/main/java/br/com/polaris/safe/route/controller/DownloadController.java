package br.com.polaris.safe.route.controller;

import br.com.polaris.safe.route.domain.export.LeituraGravacao;
import br.com.polaris.safe.route.domain.export.ListaObj;
import br.com.polaris.safe.route.repository.UsuarioComumRepository;
import br.com.polaris.safe.route.response.UsuarioComumSimplesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/downloads")
public class DownloadController {
    @Autowired
    private UsuarioComumRepository repository;

    @CrossOrigin
    @GetMapping(value = "/usuaria-comum", produces = "application/txt")
    @ResponseBody
    public ResponseEntity getUsuarioComum(){
        String corpoArquivo = "";
        ListaObj<UsuarioComumSimplesResponse> lista = new ListaObj<>(100);
        List<UsuarioComumSimplesResponse> usuarios = repository.findAllSimples();
        for (UsuarioComumSimplesResponse usuario : usuarios) {
            lista.adiciona(usuario);
        }
        String nomeArq = "cadastro-usuarias.txt";
        String header = "";
        String corpo = "";
        String trailer = "";
        int contRegDados = 0;
        Date dataDeHoje = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        header = header + "00USUARIAS";
        header = header + formatter.format(dataDeHoje);
        header = header + "01";
        LeituraGravacao.gravaRegistro(nomeArq, header);
        for(int i = 0; i< lista.getTamanho(); i++){
            corpo = "02";
            corpo = corpo + String.format("%-40s", lista.getElemento(i).getNome());
            corpo = corpo + String.format("%-30s", lista.getElemento(i).getEmail());
            corpo = corpo + String.format("%-10s", lista.getElemento(i).getDataNascimento());
            corpo = corpo + String.format("%-14s", lista.getElemento(i).getNumeroTelefone());
            corpo = corpo + String.format("%-11s", lista.getElemento(i).getReceberDicas());
            corpo = corpo + String.format("%-10s", lista.getElemento(i).getIsAprovada());
            ++contRegDados;
            LeituraGravacao.gravaRegistro(nomeArq, corpo);
        }
        trailer = trailer + "01";
        trailer = trailer + String.format("%5d", contRegDados);
        LeituraGravacao.gravaRegistro(nomeArq, trailer);
        corpoArquivo = LeituraGravacao.leArquivo(nomeArq);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=cadastro-usuarias.txt");
        if(!corpoArquivo.equals("")){
            return new ResponseEntity(corpoArquivo, headers, 200);
        }else {
            return ResponseEntity.status(404).build();
        }
    }
}
