package br.com.connect.crm.domain.movimentacao.repository;

import br.com.connect.crm.domain.movimentacao.entity.Movimentacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    @Query("SELECT m FROM Movimentacao m JOIN FETCH m.proposta JOIN FETCH m.entidade")
    Page<Movimentacao> findAll(Pageable paginacao);
}

