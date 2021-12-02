package br.com.polaris.safe.route.request;

import br.com.polaris.safe.route.domain.Endereco;
import br.com.polaris.safe.route.domain.UsuarioComum;

import javax.validation.constraints.NotNull;

public class UsuariaEnderecoRequest {

    @NotNull
    private UsuarioComum usuaria;

    @NotNull
    private Endereco endereco;

    public UsuarioComum getUsuaria() {
        return usuaria;
    }

    public void setUsuaria(UsuarioComum usuaria) {
        this.usuaria = usuaria;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
