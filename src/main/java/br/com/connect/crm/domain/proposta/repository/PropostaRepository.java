package br.com.connect.crm.domain.proposta.repository;

import br.com.connect.crm.domain.proposta.entity.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
}
