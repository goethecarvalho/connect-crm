package br.com.connect.crm.domain.movimentacao.entity;

import br.com.connect.crm.domain.entidade.entity.Entidade;
import br.com.connect.crm.domain.proposta.entity.Proposta;
import br.com.connect.crm.domain.movimentacao.vo.DadosMovimentacao;
import br.com.connect.crm.domain.movimentacao.vo.TipoMovimentacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "movimentacoes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private LocalDate data;
    private BigDecimal valor;
    @Enumerated(EnumType.ORDINAL)
    private TipoMovimentacao tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entidade", nullable = false)
    private Entidade entidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proposta", nullable = false)
    private Proposta proposta;

    public Movimentacao(DadosMovimentacao dados, Entidade entidadeDados, Proposta propostaDados) {
        this.descricao = dados.descricao();
        this.data = dados.data();
        this.valor = dados.valor();
        this.tipo = dados.tipo();
        this.entidade = entidadeDados;
        this.proposta = propostaDados;
    }

    public void atualizarDados(DadosMovimentacao dados) {
        this.descricao = dados.descricao();
        this.data = dados.data();
        this.valor = dados.valor();
        this.tipo = dados.tipo();
        this.entidade = entidade;
        this.proposta = proposta;
    }

}
