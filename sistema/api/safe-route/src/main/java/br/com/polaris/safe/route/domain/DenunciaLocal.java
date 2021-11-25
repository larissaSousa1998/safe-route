package br.com.polaris.safe.route.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "denunciaLocal")
public class DenunciaLocal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "descricao", length = 255)
    private String descricao;

    @Column(name = "local", length = 150)
    private String local;

    @Column(name = "fonte")
    private String fonte;

    @ManyToOne
    @JoinColumn(name = "idUsuaria")
    @JsonIgnoreProperties({"selfie","documento","foto"})
    private UsuarioComum usuaria;

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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public UsuarioComum getUsuaria() {
        return usuaria;
    }

    public void setUsuaria(UsuarioComum usuaria) {
        this.usuaria = usuaria;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }
}
