package br.com.polaris.safe.route.domain;

import javax.validation.constraints.NotNull;

public class UsuariaDenunciaLocal {
    @NotNull
    private UsuarioComum usuaria;

    @NotNull
    private DenunciaLocal denunciaLocal;

    public UsuarioComum getUsuaria() {
        return usuaria;
    }

    public void setUsuaria(UsuarioComum usuaria) {
        this.usuaria = usuaria;
    }

    public DenunciaLocal getDenunciaLocal() {
        return denunciaLocal;
    }

    public void setDenunciaLocal(DenunciaLocal denunciaLocal) {
        this.denunciaLocal = denunciaLocal;
    }
}
