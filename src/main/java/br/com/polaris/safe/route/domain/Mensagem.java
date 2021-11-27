package br.com.polaris.safe.route.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "mensagem")
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @NotNull
    @Size(max = 300)
    private String descricao;

    @NotNull
    private LocalDate dataEnvio;

    @NotNull
    private Integer idUsuaria;

    @NotNull
    private Integer idGrupoLocomocao;

    public Mensagem() {
    }

    public Mensagem(Integer id, String descricao, LocalDate dataEnvio, Integer idUsuaria, Integer idGrupoLocomocao) {
        this.id = id;
        this.descricao = descricao;
        this.dataEnvio = dataEnvio;
        this.idUsuaria = idUsuaria;
        this.idGrupoLocomocao = idGrupoLocomocao;
    }

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

    public LocalDate getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDate dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public Integer getIdUsuaria() {
        return idUsuaria;
    }

    public void setIdUsuaria(Integer idUsuaria) {
        this.idUsuaria = idUsuaria;
    }

    public Integer getIdGrupoLocomocao() {
        return idGrupoLocomocao;
    }

    public void setIdGrupoLocomocao(Integer idGrupoLocomocao) {
        this.idGrupoLocomocao = idGrupoLocomocao;
    }
}
