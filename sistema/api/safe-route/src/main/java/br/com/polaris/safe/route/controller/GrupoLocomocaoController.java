package br.com.polaris.safe.route.controller;

import br.com.polaris.safe.route.domain.GrupoLocomocao;
import br.com.polaris.safe.route.repository.GrupoLocomocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos-locomocao")
public class GrupoLocomocaoController {

    @Autowired
    private GrupoLocomocaoRepository repository;

    @GetMapping
    public ResponseEntity GetGrupos() {
        List<GrupoLocomocao> grupoLocomocao = repository.findAll();
        if (grupoLocomocao.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(grupoLocomocao);
        }
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity getGruposById(@PathVariable Integer id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @CrossOrigin
    @GetMapping("/participante/{id}")
    public ResponseEntity getGruposByIdParticipante(@PathVariable int id){
        List<GrupoLocomocao> grupoLocomocao = repository.findAllByIdParticipante(id);
        if (grupoLocomocao.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(grupoLocomocao);
        }

    }

    @PostMapping
    public ResponseEntity postGrupos(@RequestBody GrupoLocomocao grupoLocomocao) {
        repository.save(grupoLocomocao);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGruposById(@PathVariable Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity putGruposById(@PathVariable Integer id, @RequestBody GrupoLocomocao grupoLocomocao){
        if(repository.existsById(id)){
            repository.save(grupoLocomocao);
            return ResponseEntity.status(200).build();
        }else{
            return ResponseEntity.status(404).build();
        }
    }

    @CrossOrigin
    @GetMapping("/descobrir/{id}")
    public ResponseEntity getDescobrirGrupos(@PathVariable int id){
        List<GrupoLocomocao> gruposLocomocao = repository.findAllByIdUsuariaDescobrir(id);

        if (gruposLocomocao.isEmpty()){
            return ResponseEntity.status(204).build();
       } else {
            return ResponseEntity.status(200).body(gruposLocomocao);
        }
    }
}
