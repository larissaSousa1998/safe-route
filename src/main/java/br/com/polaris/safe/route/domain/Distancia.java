package br.com.polaris.safe.route.domain;

public class Distancia {
    private Integer idViagem;
    private Double distancia;
    private String latitudeOrigem;
    private String longitudeOrigem;
    private Integer id;
    private String nome;
    private String dataCriacaoGrupo;
    private Boolean isPrivado;

    public Integer getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(Integer idViagem) {
        this.idViagem = idViagem;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public String getLatitudeOrigem() {
        return latitudeOrigem;
    }

    public void setLatitudeOrigem(String latitudeOrigem) {
        this.latitudeOrigem = latitudeOrigem;
    }

    public String getLongitudeOrigem() {
        return longitudeOrigem;
    }

    public void setLongitudeOrigem(String longitudeOrigem) {
        this.longitudeOrigem = longitudeOrigem;
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

    public String getDataCriacaoGrupo() {
        return dataCriacaoGrupo;
    }

    public void setDataCriacaoGrupo(String dataCriacaoGrupo) {
        this.dataCriacaoGrupo = dataCriacaoGrupo;
    }

    public Boolean getPrivado() {
        return isPrivado;
    }

    public void setPrivado(Boolean privado) {
        isPrivado = privado;
    }
}
