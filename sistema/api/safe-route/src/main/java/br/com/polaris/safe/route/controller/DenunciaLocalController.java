package br.com.polaris.safe.route.controller;

import br.com.polaris.safe.route.domain.DenunciaLocal;
import br.com.polaris.safe.route.domain.export.LeituraDenuncia;
import br.com.polaris.safe.route.domain.export.LeituraGravacao;
import br.com.polaris.safe.route.repository.DenunciaLocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/denuncias-local")
public class DenunciaLocalController {

    @Autowired
    private DenunciaLocalRepository repository;

    @CrossOrigin
    @GetMapping
    public ResponseEntity getDenunciasLocal() {
        List<DenunciaLocal> denunciasLocal = repository.findAll();

        if(denunciasLocal.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(denunciasLocal);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getDenunciaLocal(@PathVariable int id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity postDenunciaLocal(@RequestBody DenunciaLocal denunciaLocal) {
        repository.save(denunciaLocal);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity putDenunciaLocal(@PathVariable int id, @RequestBody DenunciaLocal denunciaLocal) {
        if(repository.existsById(id)) {
            denunciaLocal.setId(id);
            repository.save(denunciaLocal);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity deleteDenunciaLocal(@PathVariable int id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
    @CrossOrigin
    @PostMapping("/anexo")
    public ResponseEntity criarAnexo(@RequestParam MultipartFile arquivo) throws IOException {
        List<DenunciaLocal> lista = new ArrayList<>();
        if(arquivo.isEmpty()){
            return ResponseEntity.status(204).body("Arquivo não enviado");
        }

        System.out.println("Recebendo um arquivo de nome:"+ arquivo.getOriginalFilename());
        byte[] conteudo = arquivo.getBytes();
        Path path = Paths.get(arquivo.getOriginalFilename());
        Files.write(path, conteudo);
        LeituraGravacao leituraGravacao = new LeituraGravacao();
        LeituraDenuncia ld = new LeituraDenuncia();
        adicionarViaAnexo(ld.leArquivoDenuncia(arquivo.getOriginalFilename()));

        return ResponseEntity.status(201).build();
    }

    public void adicionarViaAnexo(List<DenunciaLocal> denuncia){
        for (DenunciaLocal h : denuncia) {
            repository.save(h);
        }
    }
}