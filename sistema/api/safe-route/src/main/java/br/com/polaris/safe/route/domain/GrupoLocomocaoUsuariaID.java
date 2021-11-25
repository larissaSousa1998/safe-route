package br.com.polaris.safe.route.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GrupoLocomocaoUsuariaID implements Serializable {

    @Column(name = "idUsuaria")
    private Integer usuaria;

    @Column(name = "idGrupoLocomocao")
    private Integer grupoLocomocao;


    public Integer getUsuaria() {
        return usuaria;
    }

    public void setUsuaria(Integer usuaria) {
        this.usuaria = usuaria;
    }

    public Integer getGrupoLocomocao() {
        return grupoLocomocao;
    }

    public void setGrupoLocomocao(Integer grupoLocomocao) {
        this.grupoLocomocao = grupoLocomocao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoLocomocaoUsuariaID that = (GrupoLocomocaoUsuariaID) o;
        return usuaria.equals(that.usuaria) && grupoLocomocao.equals(that.grupoLocomocao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuaria, grupoLocomocao);
    }
}
