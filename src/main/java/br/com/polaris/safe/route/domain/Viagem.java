package br.com.polaris.safe.route.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "viagem")
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String longitudeOrigem;
    private String longitudeDestino;
    private String latitudeOrigem;
    private String latitudeDestino;

    @OneToMany(mappedBy = "viagem", cascade = CascadeType.ALL)
    private List<DataViagem> datasViagem;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "idGrupoLocomocao")
    private GrupoLocomocao grupoLocomocao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLongitudeOrigem() {
        return longitudeOrigem;
    }

    public void setLongitudeOrigem(String longitudeOrigem) {
        this.longitudeOrigem = longitudeOrigem;
    }

    public String getLongitudeDestino() {
        return longitudeDestino;
    }

    public void setLongitudeDestino(String longitudeDestino) {
        this.longitudeDestino = longitudeDestino;
    }

    public String getLatitudeOrigem() {
        return latitudeOrigem;
    }

    public void setLatitudeOrigem(String latitudeOrigem) {
        this.latitudeOrigem = latitudeOrigem;
    }

    public String getLatitudeDestino() {
        return latitudeDestino;
    }

    public void setLatitudeDestino(String latitudeDestino) {
        this.latitudeDestino = latitudeDestino;
    }

    public List<DataViagem> getDatasViagem() {
        return datasViagem;
    }

    public void setDatasViagem(List<DataViagem> datasViagem) {
        this.datasViagem = datasViagem;
    }

    public GrupoLocomocao getGrupoLocomocao() {
        return grupoLocomocao;
    }

    public void setGrupoLocomocao(GrupoLocomocao grupoLocomocao) {
        this.grupoLocomocao = grupoLocomocao;
    }
}


