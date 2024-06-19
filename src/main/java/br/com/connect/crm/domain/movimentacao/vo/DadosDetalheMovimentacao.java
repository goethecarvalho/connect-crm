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

    public DadosDetalheMovimentacao(Movimentacao movimentacao) {

        this(movimentacao.getId(), movimentacao.getDescricao(), movimentacao.getData(), movimentacao.getValor(), movimentacao.getEntidade(), movimentacao.getPropósta(), movimentacao.getTipo());
    }

}
