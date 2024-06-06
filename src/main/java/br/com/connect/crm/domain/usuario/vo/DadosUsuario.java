package br.com.connect.crm.domain.usuario.vo;

import java.io.Serializable;

public record DadosUsuario(
        Long id,
        String nome,
        String email,
        String login,
        String senha) implements Serializable {

    public DadosUsuario(String nome, String email, String login, String senha) {
        this(null, nome, email, login, senha);
    }

}
