package br.com.connect.crm.domain.proposta.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosProposta(
        Long id,
        String descricao,
        LocalDate data,
        BigDecimal valor,
        Long entidade,
        TipoProposta tipo,
        StatusProposta status
        ) implements Serializable {

    public DadosProposta(String descricao, LocalDate data, BigDecimal valor, Long entidade, TipoProposta tipo, StatusProposta status) {
        this(null, descricao, data, valor, entidade, tipo, status);
    }

}
