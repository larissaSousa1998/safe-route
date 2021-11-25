package br.com.polaris.safe.route.controller;

import br.com.polaris.safe.route.domain.ContatoConfianca;
import br.com.polaris.safe.route.repository.ContatoConfiancaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contatos-confianca")
public class ContatoConfiancaController {

    @Autowired
    private ContatoConfiancaRepository repository;

    @GetMapping()
    public ResponseEntity getContatosConfianca() {
        List<ContatoConfianca> contatosConfianca = repository.findAll();

        if(contatosConfianca.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(contatosConfianca);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getContatoConfianca(@PathVariable int id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @PostMapping
    public ResponseEntity postContatoConfianca(@RequestBody ContatoConfianca contatoConfianca) {
        repository.save(contatoConfianca);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteContatoConfianca(@PathVariable int id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.status(200).build();
        }else {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity putContatoConfianca(@PathVariable int id, ContatoConfianca contatoConfianca) {
        if(repository.existsById(id)){
            contatoConfianca.setId(id);
            repository.save(contatoConfianca);
            return ResponseEntity.status(200).build();
        }else {
            return ResponseEntity.status(404).build();
        }
    }
}
