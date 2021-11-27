package br.com.polaris.safe.route.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "descricao", length = 100)
    @NotBlank
    private String descricao;

    @Column(name = "publicacao_idpublicacao")
    private int idPublicacao;

    @Column(name = "Usuaria_IDUsuaria")
    private int idUsuaria;

    public Comentario(){
    }

    public Comentario(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
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
}
