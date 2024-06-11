package br.com.connect.crm.domain.receita.entity;

import br.com.connect.crm.domain.entidade.entity.Entidade;
import br.com.connect.crm.domain.movimentacao.vo.DadosMovimentacao;
import br.com.connect.crm.domain.movimentacao.vo.TipoMovimentacao;
import br.com.connect.crm.domain.proposta.entity.Proposta;
import br.com.connect.crm.domain.receita.vo.DadosReceita;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "receitas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private LocalDate data;
    private BigDecimal valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proposta", nullable = false)
    private Proposta proposta;

    public Receita(DadosReceita dados, Proposta proposta) {
        this.descricao = dados.descricao();
        this.data = dados.data();
        this.valor = dados.valor();
        this.proposta = proposta;
    }

    public void atualizarDados(DadosReceita dados) {
        this.descricao = dados.descricao();
        this.data = dados.data();
        this.valor = dados.valor();
        this.proposta = proposta;
    }

}
