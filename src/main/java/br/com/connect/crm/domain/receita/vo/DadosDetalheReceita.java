package br.com.connect.crm.domain.receita.vo;

import br.com.connect.crm.domain.entidade.entity.Entidade;
import br.com.connect.crm.domain.movimentacao.entity.Movimentacao;
import br.com.connect.crm.domain.movimentacao.vo.TipoMovimentacao;
import br.com.connect.crm.domain.proposta.entity.Proposta;
import br.com.connect.crm.domain.receita.entity.Receita;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosDetalheReceita(
        Long id,
        String descricao,
        LocalDate data,
        BigDecimal valor,
        Proposta proposta) implements Serializable {

    public DadosDetalheReceita(Long id, String descricao, LocalDate data, BigDecimal valor, Proposta proposta) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.proposta = proposta;
    }

    public DadosDetalheReceita(Receita receita) {

        this(receita.getId(), receita.getDescricao(), receita.getData(), receita.getValor(), receita.getProposta());
    }

}
