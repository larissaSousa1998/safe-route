package br.com.polaris.safe.route.controller;

import br.com.polaris.safe.route.domain.GrupoLocomocaoEntrar;
import br.com.polaris.safe.route.repository.GrupoLocomocaoEntrarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grupos-entrar")
public class GrupoLocomocaoEntrarController {

    @Autowired
    private GrupoLocomocaoEntrarRepository repository;

    @CrossOrigin
    @PostMapping
    public ResponseEntity postEntrar(@RequestBody GrupoLocomocaoEntrar grupoLocomocaoEntrar){
        repository.save(grupoLocomocaoEntrar);
        return ResponseEntity.status(200).build();
    }
}
