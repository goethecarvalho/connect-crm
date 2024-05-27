package br.com.connect.crm.domain.propostas.repository;

import br.com.connect.crm.domain.propostas.entity.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
}
