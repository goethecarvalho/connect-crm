package br.com.connect.crm.domain.transacao.repository;

import br.com.connect.crm.domain.transacao.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
