package br.com.polaris.safe.route.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usuaria")
public class UsuarioComum extends Usuario{

    @Past
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataNascimento;

    @Column(length = 15)
    private String numeroTelefone;

    @NotNull
    private boolean receberDicas;

    @NotNull
    private boolean isAprovada;

    @NotNull
    private boolean isAtiva;

    @Column(name = "selfie")
    @Lob
    private byte[] selfie;

    @Column(name = "dataCadastro")
    private LocalDateTime dataCadastro;

    @OneToMany(mappedBy = "usuaria")
    private List<Endereco> enderecos;

    @OneToMany(mappedBy = "usuaria")
    private List<ContatoConfianca> contatosConfianca;

    @OneToOne(mappedBy = "usuaria")
    private Documento documento;

    @OneToMany(mappedBy = "grupoLocomocao")
    private List<GrupoLocomocaoUsuaria> gruposLocomocao;

    @OneToMany(mappedBy = "usuaria")
    @JsonIgnore
    private List<DenunciaLocal> denunciaLocal;

    @OneToMany(mappedBy = "usuaria")
    @JsonIgnore
    private List<Publicacao> publicacao;

    public List<Publicacao> getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(List<Publicacao> publicacao) {
        this.publicacao = publicacao;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public boolean isReceberDicas() {
        return receberDicas;
    }

    public void setReceberDicas(boolean receberDicas) {
        this.receberDicas = receberDicas;
    }

    public boolean isAprovada() {
        return isAprovada;
    }

    public void setAprovada(boolean aprovada) {
        isAprovada = aprovada;
    }

    public boolean isAtiva() {
        return isAtiva;
    }

    public void setAtiva(boolean ativa) {
        isAtiva = ativa;
    }

    public byte[] getSelfie() {
        return selfie;
    }

    public void setSelfie(byte[] selfie) {
        this.selfie = selfie;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<ContatoConfianca> getContatosConfianca() {
        return contatosConfianca;
    }

    public void setContatosConfianca(List<ContatoConfianca> contatosConfianca) {
        this.contatosConfianca = contatosConfianca;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public List<GrupoLocomocaoUsuaria> getGruposLocomocao() {
        return gruposLocomocao;
    }

    public void setGruposLocomocao(List<GrupoLocomocaoUsuaria> gruposLocomocao) {
        this.gruposLocomocao = gruposLocomocao;
    }

    public List<DenunciaLocal> getDenunciaLocal() {
        return denunciaLocal;
    }

    public void setDenunciaLocal(List<DenunciaLocal> denunciaLocal) {
        this.denunciaLocal = denunciaLocal;
    }
}
