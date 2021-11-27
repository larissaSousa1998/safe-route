package br.com.polaris.safe.route.response;

import java.time.LocalDate;
import java.time.Period;

public class UsuariasPorIdadeResponse {

    private long quantidade;
    private int idade;

    public UsuariasPorIdadeResponse(long quantidade, int anoNascimento) {
        this.quantidade = quantidade;
        this.idade = this.calcularIdade(anoNascimento);
    }

    public long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(long quantidade) {
        this.quantidade = quantidade;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    private int calcularIdade(int anoNascimento) {
        return LocalDate.now().getYear() - anoNascimento;
    }
}
