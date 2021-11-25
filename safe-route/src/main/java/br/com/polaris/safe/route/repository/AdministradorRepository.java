package br.com.polaris.safe.route.repository;

import br.com.polaris.safe.route.domain.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {

    @Query( value = "select * from usuaria\n" +
            "INNER JOIN grupoLocomocao_usuaria\n" +
            "ON grupoLocomocao_usuaria.idUsuaria = usuaria.id\n" +
            "WHERE idGrupoLocomocao = ?1 AND isAdministradora = 1", nativeQuery = true)
    AdminOnly findByGrupo(Integer id);

    public static interface AdminOnly{
        Integer getId();
        String getNome();
        String getSelfie();
    }

    Optional<Administrador> findByEmail(String email);

}
