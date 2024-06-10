package br.com.connect.crm.domain.usuario.vo;

import br.com.connect.crm.domain.usuario.entity.Usuario;

import java.io.Serializable;

public record DadosLoginUsuario(
        String login,
        String senha) implements Serializable {

    public DadosLoginUsuario(Usuario usuario) {
        this(usuario.getLogin(), usuario.getSenha());
    }

}
