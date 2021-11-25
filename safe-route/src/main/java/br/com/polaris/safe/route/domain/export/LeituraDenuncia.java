package br.com.polaris.safe.route.domain.export;

import br.com.polaris.safe.route.domain.DenunciaLocal;
import br.com.polaris.safe.route.domain.UsuarioComum;
import br.com.polaris.safe.route.repository.UsuarioComumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LeituraDenuncia {

    public List<DenunciaLocal> leArquivoDenuncia(String nomeArq) {
        ArrayList<DenunciaLocal> lista = new ArrayList<DenunciaLocal>();
        BufferedReader entrada = null;
        int contRegistro = 0;
        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException var13) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", var13.getMessage());
        }
        try {
            for(String registro = entrada.readLine(); registro != null; registro = entrada.readLine()) {
                DenunciaLocal denunciaLocal = new DenunciaLocal();
                String tipoRegistro = registro.substring(0, 2);
                if (tipoRegistro.equals("02")) {
                    String aux = registro.substring(2, 7).trim();
                    UsuarioComum uc = new UsuarioComum();
                    uc.setId(Integer.parseInt(aux));
                        denunciaLocal.setUsuaria(uc);
                        denunciaLocal.setLocal(registro.substring(7, 107).trim());
                        denunciaLocal.setFonte(registro.substring(107, 207).trim());
                        denunciaLocal.setDescricao(registro.substring(207, 462).trim());
                        lista.add(denunciaLocal);
                        contRegistro++;
                }
            }
            entrada.close();
        } catch (IOException var14) {
            System.err.printf("Erro ao ler arquivo: %s.\n", var14.getMessage());
        }
        return lista;

    }
}
