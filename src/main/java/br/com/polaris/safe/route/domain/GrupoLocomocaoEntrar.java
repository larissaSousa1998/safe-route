package br.com.polaris.safe.route.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "grupoLocomocao_Usuaria")
public class GrupoLocomocaoEntrar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "idUsuaria")
    private Integer idUsuaria;

    @Column(name = "idGrupoLocomocao")
    private Integer idGrupoLocomocao;

    @Column(name = "dataEntradaUsuaria")
    private LocalDate dataEntrada;

    @Column(name = "isAdministradora")
    private Boolean isAdministradora;

    public GrupoLocomocaoEntrar(){
    }

    public GrupoLocomocaoEntrar(Integer id, Integer idUsuaria, Integer idGrupoLocomocao, LocalDate dataEntrada) {
        this.id = id;
        this.idUsuaria = idUsuaria;
        this.idGrupoLocomocao = idGrupoLocomocao;
        this.dataEntrada = dataEntrada;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUsuaria() {
        return idUsuaria;
    }

    public void setIdUsuaria(Integer idUsuaria) {
        this.idUsuaria = idUsuaria;
    }

    public Integer getIdGrupoLocomocao() {
        return idGrupoLocomocao;
    }

    public void setIdGrupoLocomocao(Integer idGrupoLocomocao) {
        this.idGrupoLocomocao = idGrupoLocomocao;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Boolean getAdministradora() {
        return isAdministradora;
    }

    public void setAdministradora(Boolean administradora) {
        isAdministradora = administradora;
    }
}
