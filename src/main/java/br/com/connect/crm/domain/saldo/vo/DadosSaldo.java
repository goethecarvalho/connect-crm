package br.com.connect.crm.domain.saldo.vo;


import br.com.connect.crm.domain.movimentacao.vo.TipoMovimentacao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosSaldo(
        Long id,
        LocalDate data,
        BigDecimal valor,
        Long proposta
        ) implements Serializable {


        public DadosSaldo(LocalDate data, BigDecimal valor, Long proposta) {
                this(null, data, valor, proposta);
        }
}
