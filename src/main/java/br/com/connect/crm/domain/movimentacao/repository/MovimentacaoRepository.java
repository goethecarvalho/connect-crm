package br.com.connect.crm.domain.movimentacao.repository;

import br.com.connect.crm.domain.movimentacao.entity.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
}
