package br.com.polaris.safe.route.response;

import br.com.polaris.safe.route.domain.UsuarioComum;

import java.time.LocalDate;
public class UsuarioComumSimplesResponse {

    private String nome;

    private String email;

    private LocalDate dataNascimento;

    private String numeroTelefone;

    private String receberDicas;

    private String isAprovada;

    public UsuarioComumSimplesResponse(UsuarioComum usuarioComum) {
        this.nome = usuarioComum.getNome();
        this.email = usuarioComum.getEmail();
        this.dataNascimento = usuarioComum.getDataNascimento();
        this.numeroTelefone = usuarioComum.getNumeroTelefone();
        this.receberDicas = usuarioComum.isReceberDicas() ? "Ativadas" : "Desativadas";
        this.isAprovada = usuarioComum.isAprovada() ? "Aprovada" : "Reprovada";
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public String getReceberDicas() {
        return receberDicas;
    }

    public String getIsAprovada() {
        return isAprovada;
    }
}
