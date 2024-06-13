package br.com.connect.crm.domain.usuario.vo;

import java.io.Serializable;

public record DadosUsuario(
        Long id,
        String login,
        String senha) implements Serializable {

    public DadosUsuario(String login, String senha) {
        this(null, login, senha);
    }

}
