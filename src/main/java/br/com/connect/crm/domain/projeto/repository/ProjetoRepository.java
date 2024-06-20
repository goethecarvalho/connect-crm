package br.com.connect.crm.domain.projeto.repository;

import br.com.connect.crm.domain.projeto.entity.Projeto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    @Query("SELECT p FROM Projeto p JOIN FETCH p.entidade")
    Page<Projeto> findAll(Pageable paginacao);

    @Query("SELECT p FROM Projeto p JOIN FETCH p.entidade where p.id = :id")
    Optional<Projeto> findById(Long id);


}
