package br.com.polaris.safe.route.controller;

import br.com.polaris.safe.route.domain.Comentario;
import br.com.polaris.safe.route.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioRepository repository;

    @GetMapping
    public ResponseEntity getComentarios(){
        List<Comentario> comentarios = repository.findAll();

        if(comentarios.isEmpty()){
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(comentarios);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getComentario(@PathVariable int id){
        return ResponseEntity.of(repository.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteComentario(@RequestBody int id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity putComentario(@RequestBody int id, @RequestBody Comentario comentario){
        if(repository.existsById(id)){
            comentario.setId(id);
            repository.save(comentario);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping
    public ResponseEntity postComentario(@RequestBody Comentario comentario){
        repository.save(comentario);
        return ResponseEntity.status(200).build();
    }
}
