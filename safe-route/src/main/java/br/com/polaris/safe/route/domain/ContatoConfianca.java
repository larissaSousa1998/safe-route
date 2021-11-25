package br.com.polaris.safe.route.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ContatoConfianca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 2, max = 40)
    private String nome;

    @Email
    @NotNull
    @Size(min = 6, max = 40)
    private String email;

    @NotNull
    @Size(min = 9, max = 15)
    private String numeroTelefone;

    @ManyToOne
    @JoinColumn(name = "idUsuaria")
    @JsonIgnore
    private UsuarioComum usuaria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public UsuarioComum getUsuaria() {
        return usuaria;
    }

    public void setUsuaria(UsuarioComum usuaria) {
        this.usuaria = usuaria;
    }
}
