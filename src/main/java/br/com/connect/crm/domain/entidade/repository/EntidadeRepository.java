package br.com.connect.crm.domain.entidade.repository;

import br.com.connect.crm.domain.entidade.entity.Entidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntidadeRepository extends JpaRepository<Entidade, Long> {
}
