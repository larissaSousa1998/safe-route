package br.com.polaris.safe.route.controller;

import br.com.polaris.safe.route.domain.Documento;
import br.com.polaris.safe.route.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {

    @Autowired
    private DocumentoRepository repository;

    @GetMapping
    public ResponseEntity getDocumentos() {
        List<Documento> documentos = repository.findAll();

        if(documentos.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(documentos);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getDocumento(@PathVariable int id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @PostMapping
    public ResponseEntity postDocumento(@RequestBody Documento documento) {
        repository.save(documento);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDocumento(@PathVariable int id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity putDocumento(@PathVariable int id, @RequestBody Documento documento) {
        if(repository.existsById(id)) {
            documento.setId(id);
            repository.save(documento);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }


//    @CrossOrigin
//    @GetMapping("/usuaria/{id}")
//    public ResponseEntity getDocumentoUsuaria(@PathVariable int id) {
//        return ResponseEntity.of(repository.findByIdUsuaria(id));
//    }

}
