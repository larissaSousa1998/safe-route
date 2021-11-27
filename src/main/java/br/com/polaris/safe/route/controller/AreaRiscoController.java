package br.com.polaris.safe.route.controller;

import br.com.polaris.safe.route.domain.AreaRisco;
import br.com.polaris.safe.route.repository.AreaRiscoRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/areas-risco")
public class AreaRiscoController {
    @Autowired
    public AreaRiscoRepository repository;

    @CrossOrigin
    @GetMapping
    public ResponseEntity getAreasRisco(){
        List<AreaRisco> areaRisco = repository.findAll();
        if (areaRisco.isEmpty()){
            return ResponseEntity.status(204).build();
        }else {
            return ResponseEntity.status(200).body(areaRisco);
        }
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity postAreaRisco(@RequestBody AreaRisco areaRisco) {
        repository.save(areaRisco);
        return ResponseEntity.status(201).build();
    }

    @CrossOrigin
    @GetMapping("/total")
    public ResponseEntity getTotalAreasRisco() {
        return ResponseEntity.status(200).body(repository.count());
    }
}
