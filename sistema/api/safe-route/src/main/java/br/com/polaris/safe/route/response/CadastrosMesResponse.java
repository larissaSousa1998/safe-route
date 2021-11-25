package br.com.polaris.safe.route.response;

public class CadastrosMesResponse {

    private int numeroMes;
    private long cadastros;

    public CadastrosMesResponse(int numeroMes, long cadastros) {
        this.numeroMes = numeroMes;
        this.cadastros = cadastros;
    }

    public int getNumeroMes() {
        return numeroMes;
    }

    public void setNumeroMes(int numeroMes) {
        this.numeroMes = numeroMes;
    }

    public long getCadastros() {
        return cadastros;
    }

    public void setCadastros(long cadastros) {
        this.cadastros = cadastros;
    }
}
