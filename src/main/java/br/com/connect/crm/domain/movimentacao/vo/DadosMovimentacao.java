package br.com.connect.crm.domain.movimentacao.vo;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosMovimentacao(
        Long id,
        String descricao,
        LocalDate data,
        BigDecimal valor,
        Long entidade,
        Long proposta,
        TipoMovimentacao tipo
        ) implements Serializable {

    public DadosMovimentacao(Long id, String descricao, LocalDate data, BigDecimal valor, TipoMovimentacao tipo, Long entidade, Long proposta) {
        this(null, descricao, data, valor, entidade, proposta, tipo);
    }
}
