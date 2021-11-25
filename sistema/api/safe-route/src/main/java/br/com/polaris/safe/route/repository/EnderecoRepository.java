package br.com.polaris.safe.route.repository;

import br.com.polaris.safe.route.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

//    List<Endereco> findAllByIdUsuaria(int idUsuaria);

}
