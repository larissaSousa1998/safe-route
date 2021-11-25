package br.com.polaris.safe.route.repository;

import br.com.polaris.safe.route.domain.ContatoConfianca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoConfiancaRepository extends JpaRepository<ContatoConfianca, Integer> {
}
