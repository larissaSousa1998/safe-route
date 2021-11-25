package br.com.polaris.safe.route.repository;

import br.com.polaris.safe.route.domain.GrupoLocomocao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GrupoLocomocaoRepository extends JpaRepository<GrupoLocomocao,Integer> {

    @Query(value="select distinct grupoLocomocao.* from grupoLocomocao_usuaria " +
                "inner join grupoLocomocao " +
                    "on grupoLocomocao_usuaria.idGrupoLocomocao = grupoLocomocao.id " +
                        "where grupoLocomocao_usuaria.idGrupoLocomocao NOT IN " +
                            "(select idGrupoLocomocao from grupoLocomocao_usuaria " +
                                "where grupoLocomocao_usuaria.idUsuaria = ?1);", nativeQuery = true)
    List<GrupoLocomocao> findAllByIdUsuariaDescobrir(int id);

    @Query(value = "select g from GrupoLocomocao g " +
            "join g.participantes p " +
            "where p.usuaria.id = ?1")
    List<GrupoLocomocao> findAllByIdParticipante(int id);

}
