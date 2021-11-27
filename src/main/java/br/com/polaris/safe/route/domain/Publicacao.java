package br.com.polaris.safe.route.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "publicacao")
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "descricao", length = 255)
    private String descricao;

    @Column(name = "foto")
    private byte[] foto;

    @Column(name = "curtidas")
    private int curtidas;

    @Column(name = "titulo")
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "idUsuaria")
    @JsonIgnoreProperties({"enderecos","contatosConfianca", "selfie", "documento", "gruposLocomocao"})
    private UsuarioComum usuaria;

    public Publicacao() {
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public UsuarioComum getUsuaria() {
        return usuaria;
    }

    public void setUsuaria(UsuarioComum usuaria) {
        this.usuaria = usuaria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public int getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(int curtidas) {
        this.curtidas = curtidas;
    }
}
