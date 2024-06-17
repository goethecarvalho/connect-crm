package br.com.connect.crm.domain.saldo.repository;

import br.com.connect.crm.domain.saldo.entity.Saldo;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public interface SaldoRepository extends JpaRepository<Saldo, Long> {
    @Query("SELECT s FROM Saldo s JOIN FETCH s.proposta p JOIN FETCH p.entidade")
    Page<Saldo> findAll(Pageable paginacao);

    @Query("SELECT s FROM Saldo s JOIN FETCH s.proposta p JOIN FETCH p.entidade where s.id = :id")
    Optional<Saldo> findById(Long id);

    @Query("SELECT s FROM Saldo s JOIN FETCH s.proposta p JOIN FETCH p.entidade where p.id = :id")
    Optional<Saldo> findByIdProposta(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Saldo s set s.valor = s.valor - :valor, s.data = :data where s.id = :id")
    void update(@Param("id") Long id, @Param("valor") BigDecimal valor, @Param("data") LocalDate data);

}


