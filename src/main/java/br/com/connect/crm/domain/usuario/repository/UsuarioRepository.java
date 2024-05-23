package br.com.connect.crm.domain.usuario.repository;

import br.com.connect.crm.domain.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
