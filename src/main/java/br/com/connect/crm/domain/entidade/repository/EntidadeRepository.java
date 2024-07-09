package br.com.connect.crm.domain.entidade.repository;

import br.com.connect.crm.domain.entidade.entity.Entidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EntidadeRepository extends JpaRepository<Entidade, Long> {

    @Query("SELECT e FROM Entidade e where e.tipo = 4")
    Page<Entidade> findAllClients(Pageable paginacao);

    @Query("SELECT e FROM Entidade e where e.tipo in (0, 4)")
    Page<Entidade> findAllEntidadesReceita(Pageable paginacao);
}
