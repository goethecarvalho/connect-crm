package br.com.connect.crm.domain.receita.repository;

import br.com.connect.crm.domain.receita.entity.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
}
