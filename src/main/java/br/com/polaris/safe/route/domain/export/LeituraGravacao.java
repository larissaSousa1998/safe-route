package br.com.polaris.safe.route.domain.export;

import java.io.*;

public class LeituraGravacao {

    public static void gravaRegistro(String nomeArq, String registro) {
        BufferedWriter saida = null;

        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException var5) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", var5.getMessage());
        }

        try {
            saida.append(registro + "\n");
            saida.close();
        } catch (IOException var4) {
            System.err.printf("Erro ao gravar arquivo: %s.\n", var4.getMessage());
        }
    }

    public static String leArquivo(String nomeArq) {
        String arquivo = "";
        BufferedReader entrada = null;
        int contRegistro = 0;

        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException var13) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", var13.getMessage());
        }

        try {
            for(String registro = entrada.readLine(); registro != null; registro = entrada.readLine()) {
                String tipoRegistro = registro.substring(0, 2);
                int qtdRegistro = 0;
                if (tipoRegistro.equals("00")) {
                    arquivo = "Header\n";
                    arquivo += "Tipo de arquivo: " + registro.substring(2, 10) + "\n";
                    arquivo += "Data/hora de geração do arquivo: " + registro.substring(10, 29) + "\n";
                    arquivo += "Versão do layout: " + registro.substring(29, 31) + "\n";
                } else if (tipoRegistro.equals("01")) {
                    arquivo += "\nTrailer\n";
                    qtdRegistro = Integer.parseInt(registro.substring(2, 7).trim());
                    if (qtdRegistro == contRegistro) {
                        arquivo += "Quantidade de registros gravados compatível com quantidade lida";
                    } else {
                        arquivo += "Quantidade de registros gravados não confere com quantidade lida";
                    }
                } else if (tipoRegistro.equals("02")) {
                    if (contRegistro == 0) {
                        arquivo += "\n";
                        arquivo += String.format("%-40s %-30s %-10s %-14s %-11s %-9s\n", "NOME", "EMAIL", "DATA NASC.", "TELEFONE",
                                "DICAS", "STATUS");
                    }
                    String nome = registro.substring(2, 42);
                    String email = registro.substring(42, 72);
                    String data = registro.substring(72, 82);
                    String telefone = registro.substring(82, 96);
                    String dicas = registro.substring(96, 107);
                    String status = registro.substring(107, 116);

                    arquivo += String.format("%-40s %-30s %-10s %-14s %-11s %-9s\n", nome, email, data, telefone,
                    dicas, status);
                    contRegistro++;
                } else {
                    System.out.println("Tipo de registro inválido");
                }
            }
            entrada.close();
        } catch (IOException var14) {
            System.err.printf("Erro ao ler arquivo: %s.\n", var14.getMessage());
        }
        return arquivo;
    }
}
