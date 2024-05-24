package br.com.connect.crm.domain.entidades.repository;

import br.com.connect.crm.domain.entidades.entity.Entidade;
import br.com.connect.crm.domain.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntidadeRepository extends JpaRepository<Entidade, Long> {
}
