package br.com.connect.crm.domain.receita.vo;


import br.com.connect.crm.domain.movimentacao.vo.TipoMovimentacao;
import br.com.connect.crm.domain.proposta.entity.Proposta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosReceita(
        Long id,
        String descricao,
        LocalDate data,
        BigDecimal valor,
        Long proposta
        ) implements Serializable {

    public DadosReceita(Long id, String descricao, LocalDate data, BigDecimal valor, TipoMovimentacao tipo, Long proposta) {
        this(null, descricao, data, valor, proposta);
    }
}
