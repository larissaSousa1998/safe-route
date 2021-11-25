package br.com.polaris.safe.route.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 2, max = 40)
    private String descricao;

    @NotNull
    private String logradouro;

    @NotNull
    @Size(min = 1, max = 6)
    private String numero;

    @NotNull
    @Size(min = 9, max = 9)
    private String cep;

    @NotNull
    private String estado;

    @ManyToOne
    @JoinColumn(name = "idUsuaria")
    @JsonIgnore
    private UsuarioComum usuaria;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public UsuarioComum getUsuaria() {
        return usuaria;
    }

    public void setUsuaria(UsuarioComum usuaria) {
        this.usuaria = usuaria;
    }
}
