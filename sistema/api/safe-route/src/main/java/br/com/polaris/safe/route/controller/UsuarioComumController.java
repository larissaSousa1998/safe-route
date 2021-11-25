package br.com.polaris.safe.route.controller;

import br.com.polaris.safe.route.domain.FilaObj;
import br.com.polaris.safe.route.domain.UsuarioComum;
import br.com.polaris.safe.route.repository.ContatoConfiancaRepository;
import br.com.polaris.safe.route.repository.DocumentoRepository;
import br.com.polaris.safe.route.repository.EnderecoRepository;
import br.com.polaris.safe.route.repository.UsuarioComumRepository;
import br.com.polaris.safe.route.response.CadastrosMesResponse;
import br.com.polaris.safe.route.response.UsuariasPorIdadeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.font.FontRenderContext;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuarias")
public class UsuarioComumController {

    @Autowired
    private UsuarioComumRepository repository;

    @Autowired
    private ContatoConfiancaRepository contatoConfiancaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @GetMapping
    public ResponseEntity getUsuarios() {
        List<UsuarioComum> usuarios = repository.findAll();

        if(usuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(usuarios);
        }
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity getUsuario(@PathVariable int id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUsuario(@PathVariable int id) {
        Optional<UsuarioComum> usuariaOptional = repository.findById(id);

        if(usuariaOptional.isPresent()) {
            UsuarioComum usuaria = usuariaOptional.get();

            contatoConfiancaRepository.deleteAll(usuaria.getContatosConfianca());
            enderecoRepository.deleteAll(usuaria.getEnderecos());
            documentoRepository.delete(usuaria.getDocumento());
            repository.deleteById(id);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity putUsuario(@RequestBody int id, @RequestBody UsuarioComum usuarioComum) {
        if(repository.existsById(id)) {
            usuarioComum.setId(id);
            repository.save(usuarioComum);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity logaUsuario(@RequestBody Map<String, String> dados) {
        String email = dados.get("email");
        String senha = dados.get("senha");


            Optional<UsuarioComum> usuarioOptional = repository.findByEmail(email);

            if(usuarioOptional.isPresent()) {
                UsuarioComum usuario = usuarioOptional.get();
                if(usuario.getSenha().equals(senha)) {
                    return ResponseEntity.status(200).body(usuario);
                } else {
                    return ResponseEntity.status(403).build();
                }
            }

        return ResponseEntity.status(404).build();
    }

    @GetMapping("/simples")
    public ResponseEntity getUsuariosComunsSimples() {
        return ResponseEntity.status(200).body(repository.findAllSimples());
    }

    @CrossOrigin
    @GetMapping("/total/ativas-aprovadas")
    public ResponseEntity getContasAtivasAprovadas() {
        return ResponseEntity.status(200).body(repository.countByIsAprovadaAndIsAtivaEquals(true, true));
    }

    @CrossOrigin
    @GetMapping("/total/inativas-aprovadas")
    public ResponseEntity getContasIntivasAprovadas() {
        return ResponseEntity.status(200).body(repository.countByIsAprovadaAndIsAtivaEquals(true,false));
    }

    @CrossOrigin
    @GetMapping("/aprovacao")
    public ResponseEntity getUsuariasAprovacao() {
        List<UsuarioComum> usuariasAprovacao = repository.findAllByIsAprovadaEquals(false);

        FilaObj<UsuarioComum> usuarias = new FilaObj<>(usuariasAprovacao.size());

        for (UsuarioComum usuaria: usuariasAprovacao) {
            usuarias.insert(usuaria);
        }

        if(usuarias.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(usuarias);
        }
    }

    @CrossOrigin
    @PutMapping("/aprovar/{id}")
    public ResponseEntity putAprovarUsuaria(@PathVariable int id) {
        Optional<UsuarioComum> usuariaOptional = repository.findById(id);

        if(usuariaOptional.isPresent()) {
            UsuarioComum usuaria = usuariaOptional.get();

            if(usuaria.isAprovada()) {
                return ResponseEntity.status(400).build();
            } else {
                usuaria.setAprovada(true);
                usuaria.setAtiva(true);
                repository.save(usuaria);
                return ResponseEntity.status(200).build();
            }

        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @CrossOrigin
    @PutMapping("/ativar-conta/{id}")
    public ResponseEntity putAtivarConta(@PathVariable int id) {
        Optional<UsuarioComum> usuariaOptional = repository.findById(id);

        if(usuariaOptional.isPresent()) {
            UsuarioComum usuaria = usuariaOptional.get();

            if(usuaria.isAtiva()) {
                return ResponseEntity.status(400).build();
            } else {
                usuaria.setAtiva(true);
                repository.save(usuaria);
                return ResponseEntity.status(200).build();
            }

        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @CrossOrigin
    @PutMapping("/desativar-conta/{id}")
    public ResponseEntity putDesativarConta(@PathVariable int id) {
        Optional<UsuarioComum> usuariaOptional = repository.findById(id);

        if(usuariaOptional.isPresent()) {
            UsuarioComum usuaria = usuariaOptional.get();

            if(usuaria.isAtiva()) {
                usuaria.setAtiva(false);
                repository.save(usuaria);
                return ResponseEntity.status(200).build();
            } else {
                return ResponseEntity.status(400).build();
            }

        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @CrossOrigin
    @GetMapping("/total")
    public ResponseEntity getTotalUsuarias() {
        return ResponseEntity.status(200).body(repository.count());
    }

    @CrossOrigin
    @GetMapping("/total-mes")
    public ResponseEntity getTotalUsuariasCadastradasNoMes() {
        List<CadastrosMesResponse> cadastros = repository.countByMesDataCadastro();

        if(cadastros.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(cadastros);
        }
    }

    @CrossOrigin
    @GetMapping("/total-ativas")
    public ResponseEntity getTotalUsuariasAtivas() {
        return ResponseEntity.status(200).body(repository.countByIsAtivaEquals(true));
    }

    @CrossOrigin
    @GetMapping("/total-inativas")
    public ResponseEntity getTotalUsuariasInativas() {
        return ResponseEntity.status(200).body(repository.countByIsAtivaEquals(false));
    }

    @CrossOrigin
    @GetMapping("/total-analise")
    public ResponseEntity getTotalUsuariasEmAnalise() {
        return ResponseEntity.status(200).body(repository.countByIsAprovadaEquals(false));
    }

    @CrossOrigin
    @GetMapping("/total-dicas-ativadas")
    public ResponseEntity getTotalUsuariasDicasAtivadas() {
        return ResponseEntity.status(200).body(repository.countByReceberDicasEquals(true));
    }

    @CrossOrigin
    @GetMapping("/total-dicas-desativadas")
    public ResponseEntity getTotalUsuariasDicasDesativadas() {
        return ResponseEntity.status(200).body(repository.countByReceberDicasEquals(false));
    }

    @CrossOrigin
    @GetMapping("/idade")
    public ResponseEntity getUsuariaEIdade() {
        List<UsuariasPorIdadeResponse> idades = repository.countByIdade();

        if(idades.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(idades);
        }
    }
}
