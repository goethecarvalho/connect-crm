package br.com.connect.crm.domain.entidades.entity;

import br.com.connect.crm.domain.entidades.vo.DadosEntidade;
import br.com.connect.crm.domain.entidades.vo.TipoEntidade;
import br.com.connect.crm.domain.usuario.vo.DadosUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "entidades")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoEntidade tipo;

    public Entidade(DadosEntidade dados) {
        this.nome = dados.nome();
        this.tipo = dados.tipo();
    }

    public void atualizarDados(DadosEntidade dados) {
        this.nome = dados.nome();
        this.tipo = dados.tipo();
    }

}
