package br.com.connect.crm.domain.receita.vo;


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

}
