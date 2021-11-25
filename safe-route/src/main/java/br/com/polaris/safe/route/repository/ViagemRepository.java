package br.com.polaris.safe.route.repository;

import br.com.polaris.safe.route.domain.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ViagemRepository extends JpaRepository<Viagem, Integer> {

    @Query( value = " SELECT top 5 viagem.id as idViagem, " +
           " (6371 * acos( " +
           " cos( radians(?1) ) " +
           " * cos( radians( latitudeOrigem ) ) " +
           " * cos( radians( longitudeOrigem ) - radians(?2) ) " +
           " + sin( radians(?3) ) " +
           " * sin( radians( latitudeOrigem ) ) " +
           " ) " +
           " ) as distancia, latitudeOrigem, longitudeOrigem, latitudeDestino, longitudeDestino, grupoLocomocao.* " +
           " FROM viagem " +
           " INNER JOIN grupoLocomocao " +
           " ON grupoLocomocao.id = viagem.idGrupoLocomocao " +
           " WHERE (6371 * acos( " +
                   " cos( radians(?4) ) " +
           " * cos( radians( latitudeOrigem ) ) " +
           " * cos( radians( longitudeOrigem ) - radians(?5) ) " +
           " + sin( radians(?6) ) " +
           " * sin( radians( latitudeOrigem ) ) " +
           " )) < 5 AND grupoLocomocao.id NOT IN\n" +
            "        (select idGrupoLocomocao from grupoLocomocao_usuaria WHERE\n" +
            "        grupoLocomocao_usuaria.idUsuaria = ?7)" +
            "ORDER BY distancia ASC ", nativeQuery = true)

    List<DistanciaOnly> findDistancia(Double latitude1, Double longitude1, Double latitude2, Double latitude3, Double longitude2, Double latitude4, Integer id);

    public static interface DistanciaOnly{
        Integer getIdViagem();
        String getLatitudeOrigem();
        String getlongitudeOrigem();
        String getLatitudeDestino();
        String getlongitudeDestino();
        Integer getId();
        String getNome();
        LocalDate getDataCriacaoGrupo();
        Boolean getIsPrivado();
    }
}