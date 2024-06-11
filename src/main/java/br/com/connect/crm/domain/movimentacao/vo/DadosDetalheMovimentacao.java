package br.com.connect.crm.domain.movimentacao.vo;

import br.com.connect.crm.domain.entidade.entity.Entidade;
import br.com.connect.crm.domain.proposta.entity.Proposta;
import br.com.connect.crm.domain.movimentacao.entity.Movimentacao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosDetalheMovimentacao(
        Long id,
        String descricao,
        LocalDate data,
        BigDecimal valor,
        Entidade entidade,
        Proposta proposta,
        TipoMovimentacao tipo) implements Serializable {

    public DadosDetalheMovimentacao(Long id, String descricao, LocalDate data, BigDecimal valor, Entidade entidade, Proposta proposta, TipoMovimentacao tipo) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.entidade = entidade;
        this.proposta = proposta;
        this.tipo = tipo;
    }

    public DadosDetalheMovimentacao(Movimentacao transacao) {

        this(transacao.getId(), transacao.getDescricao(), transacao.getData(), transacao.getValor(), transacao.getEntidade(), transacao.getProposta(), transacao.getTipo());
    }

}
