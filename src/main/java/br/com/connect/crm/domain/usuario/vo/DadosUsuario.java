package br.com.connect.crm.domain.usuario.vo;

import java.io.Serializable;

public record DadosUsuario(
        Long id,
        String nome,
        String login,
        String senha) implements Serializable {

    public DadosUsuario(String nome, String login, String senha) {
        this(null, nome, login, senha);
    }

}
