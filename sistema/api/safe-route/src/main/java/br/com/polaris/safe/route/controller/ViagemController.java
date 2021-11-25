package br.com.polaris.safe.route.controller;

import br.com.polaris.safe.route.domain.Viagem;
import br.com.polaris.safe.route.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/viagens")
public class ViagemController {
    @Autowired
    private ViagemRepository repository;

    @GetMapping
    public ResponseEntity getViagens(){
        List<Viagem> listaViagens = repository.findAll();
        if(listaViagens.isEmpty()){
            return ResponseEntity.status(404).build();
        }else {
            return ResponseEntity.status(200).body(listaViagens);
        }
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity getViagensById(@PathVariable  Integer id){
        return ResponseEntity.of(repository.findById(id));
    }

    @PostMapping
    public ResponseEntity postViagens(@RequestBody Viagem viagem){
        repository.save(viagem);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteViagensById(@PathVariable Integer id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.status(200).build();
        }else {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity putViagensById(@PathVariable Integer id, @RequestBody Viagem viagem){
        if(repository.existsById(id)){
            repository.save(viagem);
            return ResponseEntity.status(200).build();
        } else{
            return ResponseEntity.status(404).build();
        }
    }
    @CrossOrigin
    @GetMapping("/pegar-distancia")
    public ResponseEntity pegarDistancia(@PathParam("latitude") Double latitude, @PathParam("longitude") Double longitude, @PathParam("id") Integer id){
        return ResponseEntity.status(200).body(repository.findDistancia(latitude,longitude,latitude,latitude,longitude,latitude, id));
    }
}
