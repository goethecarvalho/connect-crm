package br.com.connect.crm.domain.transacao.vo;

import br.com.connect.crm.domain.entidade.entity.Entidade;
import br.com.connect.crm.domain.proposta.entity.Proposta;
import br.com.connect.crm.domain.transacao.entity.Transacao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosDetalheTransacao(
        Long id,
        String descricao,
        LocalDate data,
        BigDecimal valor,
        Entidade entidade,
        Proposta proposta,
        TipoTransacao tipo) implements Serializable {

    public DadosDetalheTransacao(Long id, String descricao, LocalDate data, BigDecimal valor, Entidade entidade, Proposta proposta, TipoTransacao tipo) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.entidade = entidade;
        this.proposta = proposta;
        this.tipo = tipo;
    }

    public DadosDetalheTransacao(Transacao transacao) {

        this(transacao.getId(), transacao.getDescricao(), transacao.getData(), transacao.getValor(), transacao.getEntidade(), transacao.getProposta(), transacao.getTipo());
    }
}
