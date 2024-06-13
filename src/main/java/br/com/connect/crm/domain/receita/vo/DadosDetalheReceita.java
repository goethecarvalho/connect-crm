package br.com.connect.crm.domain.receita.vo;

import br.com.connect.crm.domain.proposta.entity.Proposta;
import br.com.connect.crm.domain.receita.entity.Receita;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosDetalheReceita(
        Long id,
        String descricao,
        LocalDate data,
        BigDecimal valor,
        Proposta proposta) implements Serializable {

    public DadosDetalheReceita(Receita receita) {

        this(receita.getId(), receita.getDescricao(), receita.getData(), receita.getValor(), receita.getProposta());
    }

}
