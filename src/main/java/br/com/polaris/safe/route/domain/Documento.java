package br.com.polaris.safe.route.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tipo;

    @Column(name = "fotoFrente")
    @Lob
    private byte[] fotoFrente;

    @Column(name = "fotoVerso")
    @Lob
    private byte[] fotoVerso;

    private String numeracao;

    @OneToOne
    @JoinColumn(name = "idUsuaria")
    @JsonIgnore
    private UsuarioComum usuaria;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte[] getFotoFrente() {
        return fotoFrente;
    }

    public void setFotoFrente(byte[] fotoFrente) {
        this.fotoFrente = fotoFrente;
    }

    public byte[] getFotoVerso() {
        return fotoVerso;
    }

    public void setFotoVerso(byte[] fotoVerso) {
        this.fotoVerso = fotoVerso;
    }

    public String getNumeracao() {
        return numeracao;
    }

    public void setNumeracao(String numeracao) {
        this.numeracao = numeracao;
    }

    public UsuarioComum getUsuaria() {
        return usuaria;
    }

    public void setUsuaria(UsuarioComum usuaria) {
        this.usuaria = usuaria;
    }
}
