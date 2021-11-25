package br.com.polaris.safe.route.repository;

import br.com.polaris.safe.route.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
}
