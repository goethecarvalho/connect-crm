package br.com.connect.crm.domain.propostas.entity;

import br.com.connect.crm.domain.entidades.entity.Entidade;
import br.com.connect.crm.domain.entidades.vo.DadosEntidade;
import br.com.connect.crm.domain.entidades.vo.TipoEntidade;
import br.com.connect.crm.domain.propostas.vo.DadosProposta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "propostas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private LocalDate data;
    private BigDecimal valor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entidade_id")
    private Entidade entidade;

    public Proposta(DadosProposta dados) {
        this.descricao = dados.descricao();
        this.data = dados.data();
        this.valor = dados.valor();
        this.entidade = entidade;
    }

    public void atualizarDados(DadosProposta dados, Entidade entidade) {
        this.descricao = dados.descricao();
        this.data = dados.data();
        this.valor = dados.valor();
        this.entidade = entidade;
    }

}
