package br.com.polaris.safe.route.repository;

import br.com.polaris.safe.route.domain.Publicacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Integer> {
    List<Publicacao>  findAllByOrderByIdDesc();
}
