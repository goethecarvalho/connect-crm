package br.com.connect.crm.domain.proposta.repository;

import br.com.connect.crm.domain.proposta.entity.Proposta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    @Query("SELECT p FROM Proposta p JOIN FETCH p.entidade")
    Page<Proposta> findAll(Pageable paginacao);

    @Query("SELECT p FROM Proposta p JOIN FETCH p.entidade where p.id = :id")
    Optional<Proposta> findById(Long id);


}
