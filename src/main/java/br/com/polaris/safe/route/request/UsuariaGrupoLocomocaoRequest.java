package br.com.polaris.safe.route.request;

import br.com.polaris.safe.route.domain.GrupoLocomocao;
import br.com.polaris.safe.route.domain.UsuarioComum;

import javax.validation.constraints.NotNull;

public class UsuariaGrupoLocomocaoRequest {

    @NotNull
    private UsuarioComum usuaria;

    @NotNull
    private GrupoLocomocao grupoLocomocao;

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
}
