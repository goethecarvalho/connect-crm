package br.com.connect.crm.domain.usuario.repository;

import br.com.connect.crm.domain.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("""
            select count(u) > 0
            from Usuario u
            where u.login = :login
            and u.senha = :senha
            """)
    Boolean findLoginUsuario(String login, String senha);
}
