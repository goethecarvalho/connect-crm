package br.com.connect.crm.domain.saldo.vo;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosSaldo(
        Long id,
        LocalDate data,
        BigDecimal valor,
        Long proposta
        ) implements Serializable {

}
