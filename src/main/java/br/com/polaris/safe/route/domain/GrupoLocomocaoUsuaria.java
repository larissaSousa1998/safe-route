package br.com.polaris.safe.route.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "grupoLocomocao_usuaria")
public class GrupoLocomocaoUsuaria {

    @EmbeddedId
    private GrupoLocomocaoUsuariaID grupoLocomocaoUsuaria;

    @ManyToOne
    @JsonIgnoreProperties({"gruposLocomocao","selfie","documento"})
    @JoinColumn(name = "idUsuaria",insertable = false, updatable = false)
    private UsuarioComum usuaria;

    @ManyToOne
    @JsonIgnoreProperties({"participantes"})
    @JoinColumn(name = "idGrupoLocomocao",insertable = false, updatable = false)
    private GrupoLocomocao grupoLocomocao;

    @Column(name = "isAdministradora")
    private Boolean isAdministradora;

    @Column(name = "dataEntradaUsuaria")
    private LocalDateTime dataEntradaUsuaria;

    public GrupoLocomocaoUsuariaID getGrupoLocomocaoUsuaria() {
        return grupoLocomocaoUsuaria;
    }

    public void setGrupoLocomocaoUsuaria(GrupoLocomocaoUsuariaID grupoLocomocaoUsuaria) {
        this.grupoLocomocaoUsuaria = grupoLocomocaoUsuaria;
    }

    public UsuarioComum getUsuaria() {
        return usuaria;
    }

    public void setUsuaria(UsuarioComum usuaria) {
        this.usuaria = usuaria;
    }

    public GrupoLocomocao getGrupoLocomocao() {
        return grupoLocomocao;
    }

    public void setGrupoLocomocao(GrupoLocomocao grupoLocomocao) {
        this.grupoLocomocao = grupoLocomocao;
    }

    public Boolean getAdministradora() {
        return isAdministradora;
    }

    public void setAdministradora(Boolean administradora) {
        isAdministradora = administradora;
    }

    public LocalDateTime getDataEntradaUsuaria() {
        return dataEntradaUsuaria;
    }

    public void setDataEntradaUsuaria(LocalDateTime dataEntradaUsuaria) {
        this.dataEntradaUsuaria = dataEntradaUsuaria;
    }
}
