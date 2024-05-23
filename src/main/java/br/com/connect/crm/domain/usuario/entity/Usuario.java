package br.com.connect.crm.domain.usuario.entity;

import br.com.connect.crm.domain.usuario.vo.DadosUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String login;
    private String senha;

    public Usuario(DadosUsuario dados) {
        this.nome = dados.nome();
        this.login = dados.login();
        this.senha = dados.senha();
    }

    public void atualizarDados(DadosUsuario dados) {
        this.nome = dados.nome();
        this.login = dados.login();
        this.senha = dados.senha();
    }

}
