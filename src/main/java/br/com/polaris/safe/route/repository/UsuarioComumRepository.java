package br.com.polaris.safe.route.repository;

import br.com.polaris.safe.route.domain.UsuarioComum;
import br.com.polaris.safe.route.response.CadastrosMesResponse;
import br.com.polaris.safe.route.response.UsuariasPorIdadeResponse;
import br.com.polaris.safe.route.response.UsuarioComumSimplesResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioComumRepository extends JpaRepository<UsuarioComum, Integer> {

    Optional<UsuarioComum> findByEmail(String email);
    boolean existsUsuarioComumByEmail(String email);

    @Query("select u from UsuarioComum u")
    List<UsuarioComumSimplesResponse> findAllSimples();

    List<UsuarioComum> findAllByIsAprovadaEquals(boolean value);

    int countByIsAprovadaEquals(boolean value);

    int countByIsAtivaEquals(boolean value);

    int countByReceberDicasEquals(boolean value);

    @Query("select new br.com.polaris.safe.route.response.UsuariasPorIdadeResponse (count(u.id), year(u.dataNascimento)) " +
            "from UsuarioComum u " +
            "group by year(u.dataNascimento)")
    List<UsuariasPorIdadeResponse> countByIdade();

    @Query(value = "select new br.com.polaris.safe.route.response.CadastrosMesResponse(month(u.dataCadastro), count(u.id)) " +
            "from UsuarioComum u group by month(u.dataCadastro)")
    List<CadastrosMesResponse> countByMesDataCadastro();

    @Query("select count(u.id) from UsuarioComum u where u.isAprovada = ?1 and u.isAtiva = ?2")
    int countByIsAprovadaAndIsAtivaEquals(boolean isAprovada, boolean isAtiva);

}
