package br.com.polaris.safe.route.controller;

import br.com.polaris.safe.route.domain.PilhaObj;
import br.com.polaris.safe.route.domain.Publicacao;
import br.com.polaris.safe.route.repository.PublicacaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/publicacoes")
public class PublicacaoController {

    @Autowired
    private PublicacaoRepository repository;

    @CrossOrigin
    @GetMapping
    public ResponseEntity getPublicacoes(){
        List<Publicacao> publicacoes = repository.findAllByOrderByIdDesc();

        PilhaObj<Publicacao> publicacoesPilha = new PilhaObj<>(publicacoes.size());

        for (Publicacao publicacao: publicacoes) {
            publicacoesPilha.push(publicacao);
        }

        if (publicacoesPilha.isEmpty()){
            return ResponseEntity.status(204).build();
        } else
            return ResponseEntity.status(200).body(publicacoesPilha);
    }

    @GetMapping("/{id}")
    public ResponseEntity getPublicacao(@PathVariable int id){
        return ResponseEntity.of(repository.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePublicacao(@RequestBody int id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity putPublicacao(@RequestBody int id, @RequestBody Publicacao publicacao){
        if(repository.existsById(id)){
            publicacao.setId(id);
            repository.save(publicacao);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @CrossOrigin
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity postPublicacao(@RequestParam("publicacao") String jsonPublicacao,
                                         @RequestParam("foto") MultipartFile fotoPublicacao){

        ObjectMapper mapper = new ObjectMapper();
        Publicacao publicacao = new Publicacao();

        try {
            publicacao = mapper.readValue(jsonPublicacao, Publicacao.class);
            publicacao.setFoto(fotoPublicacao.getBytes());
            repository.save(publicacao);
            return ResponseEntity.status(201).build();
        } catch ( IOException  exception) {
            exception.printStackTrace();
            return ResponseEntity.status(400).build();
        }
    }
}
