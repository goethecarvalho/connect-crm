package br.com.connect.crm.domain.receita.repository;

import br.com.connect.crm.domain.receita.entity.Receita;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    @Query("SELECT r FROM Receita r JOIN FETCH r.entidade e")
    Page<Receita> findAll(Pageable paginacao);

    @Query("SELECT r FROM Receita r JOIN FETCH r.entidade e where r.id = :id")
    Optional<Receita> findById(Long id);

    @Query("SELECT r FROM Receita r JOIN FETCH r.entidade e WHERE e.id = :id")
    Optional<Receita> findByEntidade(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Receita r set r.valor = r.valor - :valor where r.id = :id")
    void updateDebido(@Param("id") Long id, @Param("valor") BigDecimal valor);

    @Modifying
    @Transactional
    @Query("UPDATE Receita r set r.valor = r.valor + :valor where r.id = :id")
    void updateCredito(@Param("id") Long id, @Param("valor") BigDecimal valor);
}


