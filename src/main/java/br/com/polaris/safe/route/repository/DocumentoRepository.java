package br.com.polaris.safe.route.repository;

import br.com.polaris.safe.route.domain.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Integer> {

//    Optional<Documento> findByIdUsuaria(int id);

}
