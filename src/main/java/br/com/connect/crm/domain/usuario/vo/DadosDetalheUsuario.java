package br.com.connect.crm.domain.usuario.vo;

import br.com.connect.crm.domain.usuario.entity.Usuario;

import java.io.Serializable;

public record DadosDetalheUsuario (
        Long id,
        String nome,
        String email,
        String login,
        String senha) implements Serializable {

    public DadosDetalheUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getLogin(), usuario.getSenha());
    }

}
