package br.com.connect.crm.domain.transacao.vo;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosTransacao(
        Long id,
        String descricao,
        LocalDate data,
        BigDecimal valor,
        Long entidade,
        Long proposta,
        TipoTransacao tipo
        ) implements Serializable {

    public DadosTransacao(Long id, String descricao, LocalDate data, BigDecimal valor, TipoTransacao tipo, Long entidade, Long proposta) {
        this(null, descricao, data, valor, entidade, proposta, tipo);
    }
}
