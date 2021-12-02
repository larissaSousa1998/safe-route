package br.com.polaris.safe.route.controller;

import br.com.polaris.safe.route.domain.Endereco;
import br.com.polaris.safe.route.request.UsuariaEnderecoRequest;
import br.com.polaris.safe.route.domain.UsuarioComum;
import br.com.polaris.safe.route.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoRepository repository;

    @GetMapping
    public ResponseEntity getEnderecos() {
        List<Endereco> enderecos = repository.findAll();

        if(enderecos.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(enderecos);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getEndereco(@PathVariable int id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @PostMapping
    public ResponseEntity postEndereco(@RequestBody UsuariaEnderecoRequest usuariaEndereco) {
        Endereco endereco = usuariaEndereco.getEndereco();
        UsuarioComum usuaria = usuariaEndereco.getUsuaria();
        endereco.setUsuaria(usuaria);
        repository.save(endereco);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEndereco(@PathVariable int id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity putEndereco(@PathVariable int id, @RequestBody Endereco endereco) {
        if(repository.existsById(id)) {
            endereco.setId(id);
            repository.save(endereco);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @CrossOrigin
    @GetMapping("/usuaria/{id}")
    public ResponseEntity getEnderecoPorUsuaria(@PathVariable int id){
        return ResponseEntity.status(200).body(repository.findAllByUsuariaId(id));
    }

}
