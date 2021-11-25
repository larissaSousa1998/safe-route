package br.com.polaris.safe.route.repository;

import br.com.polaris.safe.route.domain.DataViagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataViagemRepository extends JpaRepository<DataViagem, Integer> {
}
