package br.com.polaris.safe.route.controller;

import br.com.polaris.safe.route.domain.*;
import br.com.polaris.safe.route.repository.ContatoConfiancaRepository;
import br.com.polaris.safe.route.repository.DocumentoRepository;
import br.com.polaris.safe.route.repository.EnderecoRepository;
import br.com.polaris.safe.route.repository.UsuarioComumRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/usuarias")
public class CadastroController {

    @Autowired
    private UsuarioComumRepository usuarioComumRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ContatoConfiancaRepository contatoConfiancaRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @CrossOrigin
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity postUsuario(@RequestParam("cadastro") String cadastroJson,
                                      @RequestParam("selfie") MultipartFile selfie,
                                      @RequestParam("fotoFrente") MultipartFile fotoFrente,
                                      @RequestParam("fotoVerso") MultipartFile fotoVerso) {

        System.out.println(cadastroJson);
        ObjectMapper mapper = new ObjectMapper();
        Cadastro cadastro = new Cadastro();

        try {
            cadastro = mapper.readValue(cadastroJson,Cadastro.class);
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
        }

        System.out.println(cadastro.toString());
        String email = cadastro.getUsuarioComum()
                .getEmail();
        
        if(usuarioComumRepository.existsUsuarioComumByEmail(email)) {
            return ResponseEntity.status(409).build();
        } else {
            try {
                Path path = Paths.get("src/main/resources/images/foto-padrao.png");

                UsuarioComum novaUsuaria = cadastro.getUsuarioComum();

                novaUsuaria.setFoto(Files.readAllBytes(path));
                novaUsuaria.setSelfie(selfie.getBytes());
                novaUsuaria.setDataCadastro(LocalDateTime.now());
                usuarioComumRepository.save(novaUsuaria);

                Optional<UsuarioComum> usuarioComumOptional = usuarioComumRepository.findByEmail(email);

                if(usuarioComumOptional.isPresent()) {
                    UsuarioComum usuaria = usuarioComumOptional.get();

                    Endereco endereco = cadastro.getEndereco();
                    ContatoConfianca contatoConfianca = cadastro.getContatoConfianca();
                    Documento documento = cadastro.getDocumento();

                    endereco.setUsuaria(usuaria);
                    contatoConfianca.setUsuaria(usuaria);
                    documento.setUsuaria(usuaria);
                    documento.setFotoFrente(fotoFrente.getBytes());
                    documento.setFotoVerso(fotoVerso.getBytes());

                    enderecoRepository.save(endereco);
                    contatoConfiancaRepository.save(contatoConfianca);
                    documentoRepository.save(documento);


                    return ResponseEntity.status(201).build();
                }

            } catch (IOException exception) {
                exception.printStackTrace();
            }

        }
        return ResponseEntity.status(409).build();
    }

}
