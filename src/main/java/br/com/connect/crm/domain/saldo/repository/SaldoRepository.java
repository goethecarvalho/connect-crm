package br.com.connect.crm.domain.saldo.repository;

import br.com.connect.crm.domain.saldo.entity.Saldo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SaldoRepository extends JpaRepository<Saldo, Long> {
    @Query("SELECT s FROM Saldo s JOIN FETCH s.proposta p JOIN FETCH p.entidade")
    Page<Saldo> findAll(Pageable paginacao);

    @Query("SELECT s FROM Saldo s JOIN FETCH s.proposta p JOIN FETCH p.entidade where s.id = :id")
    Optional<Saldo> findById(Long id);
}


