package br.com.polaris.safe.route.controller;

import br.com.polaris.safe.route.domain.*;
import br.com.polaris.safe.route.repository.DataViagemRepository;
import br.com.polaris.safe.route.repository.GrupoLocomocaoEntrarRepository;
import br.com.polaris.safe.route.repository.GrupoLocomocaoRepository;
import br.com.polaris.safe.route.repository.ViagemRepository;
import br.com.polaris.safe.route.request.UsuariaGrupoLocomocaoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/grupos-locomocao")
public class GrupoLocomocaoController {

    @Autowired
    private GrupoLocomocaoRepository repository;

    @Autowired
    private GrupoLocomocaoEntrarRepository entrarRepository;

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
    public ResponseEntity postGrupos(@RequestBody UsuariaGrupoLocomocaoRequest usuariaGrupoLocomocaoRequest) {
        GrupoLocomocao grupoLocomocao = usuariaGrupoLocomocaoRequest.getGrupoLocomocao();
        grupoLocomocao.setData(LocalDate.now());
        grupoLocomocao.setPrivado(false);

        grupoLocomocao = repository.save(grupoLocomocao);

        Viagem viagem = grupoLocomocao.getViagem();
        viagem.setGrupoLocomocao(grupoLocomocao);
        viagem.getDatasViagem().forEach(dataViagem -> dataViagem.setViagem(viagem));

        repository.save(grupoLocomocao);

        this.registerUserInNewGroup(grupoLocomocao, usuariaGrupoLocomocaoRequest.getUsuaria());

        return ResponseEntity.status(200).build();
    }

    private void registerUserInNewGroup(GrupoLocomocao grupoLocomocao, UsuarioComum usuaria) {
        GrupoLocomocaoEntrar grupoLocomocaoEntrar = new GrupoLocomocaoEntrar();
        grupoLocomocaoEntrar.setIdGrupoLocomocao(grupoLocomocao.getId());
        grupoLocomocaoEntrar.setIdUsuaria(usuaria.getId());
        grupoLocomocaoEntrar.setAdministradora(true);
        grupoLocomocaoEntrar.setDataEntrada(LocalDate.now());
        entrarRepository.save(grupoLocomocaoEntrar);
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
