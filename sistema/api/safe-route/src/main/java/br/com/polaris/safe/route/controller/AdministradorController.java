package br.com.polaris.safe.route.controller;

import br.com.polaris.safe.route.domain.Administrador;
import br.com.polaris.safe.route.domain.Usuario;
import br.com.polaris.safe.route.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorRepository repository;

    @GetMapping
    public ResponseEntity getAdministradores() {
        List<Administrador> administradores = repository.findAll();

        if(administradores.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(administradores);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getAdministrador(@PathVariable int id) {

        return ResponseEntity.of(repository.findById(id));
    }

    @CrossOrigin
    @GetMapping("/grupo")
    public ResponseEntity getAdministradoraGrupo(@PathParam("id") Integer id) {
        return ResponseEntity.status(200).body(repository.findByGrupo(id));
    }

    @PostMapping
    public ResponseEntity postAdministrador(@RequestBody Administrador administrador) {
        repository.save(administrador);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAdministrador(@PathVariable int id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity putAdministrador(@PathVariable int id, @RequestBody Administrador administrador) {
        if(repository.existsById(id)) {
            administrador.setId(id);
            repository.save(administrador);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @CrossOrigin
    @PostMapping("/login")
    public Usuario loginAdministrador(@RequestBody Map<String, String> dados) {
        String email = dados.get("email");
        String senha = dados.get("senha");


            Optional<Administrador> administradorOptional = repository.findByEmail(email);

            if(administradorOptional.isPresent()) {
                Administrador administrador = administradorOptional.get();
                return administrador.getSenha().equals(senha) ? administrador : null;
            }

        return null;
    }

}
