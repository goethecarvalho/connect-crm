package br.com.connect.crm.domain.entidade.entity;

import br.com.connect.crm.domain.entidade.vo.DadosDetalheEntidade;
import br.com.connect.crm.domain.entidade.vo.DadosEntidade;
import br.com.connect.crm.domain.entidade.vo.TipoEntidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "entidades")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Entidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Enumerated(EnumType.ORDINAL)
    private TipoEntidade tipo;

    public Entidade(DadosEntidade dados) {
        this.nome = dados.nome().toUpperCase();
        this.tipo = dados.tipo();
    }

    public void atualizarDados(DadosEntidade dados) {
        this.nome = dados.nome();
        this.tipo = dados.tipo();
    }

    public Entidade(DadosDetalheEntidade dados) {
        this.id = dados.id();
        this.nome = dados.nome();
        this.tipo = dados.tipo();
    }

}
