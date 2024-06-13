package br.com.connect.crm.domain.receita.repository;

import br.com.connect.crm.domain.proposta.entity.Proposta;
import br.com.connect.crm.domain.receita.entity.Receita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    @Query("SELECT r FROM Receita r JOIN FETCH r.proposta p JOIN FETCH p.entidade")
    Page<Receita> findAll(Pageable paginacao);

    @Query("SELECT r FROM Receita r JOIN FETCH r.proposta p JOIN FETCH p.entidade where r.id = :id")
    Optional<Receita> findById(Long id);
}


