package br.com.polaris.safe.route.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "grupoLocomocao")
public class GrupoLocomocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Column(name = "dataCriacaoGrupo")
    private LocalDate data;

    @Column(name = "isPrivado")
    private Boolean privado;

    @OneToOne(mappedBy = "grupoLocomocao", cascade = {CascadeType.ALL})
    private Viagem viagem;

    @OneToMany(mappedBy = "grupoLocomocao")
    private List<GrupoLocomocaoUsuaria> participantes;

    public List<GrupoLocomocaoUsuaria> getParticipantes() {
        return participantes;
    }

    @JsonIgnoreProperties({"grupoLocomocao", "grupoLocomocaoUsuaria"})
    public void setParticipantes(List<GrupoLocomocaoUsuaria> participantes) {
        this.participantes = participantes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Boolean getPrivado() {
        return privado;
    }

    public void setPrivado(Boolean privado) {
        this.privado = privado;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

}
