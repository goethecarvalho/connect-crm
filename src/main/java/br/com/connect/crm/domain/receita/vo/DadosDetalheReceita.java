package br.com.connect.crm.domain.receita.vo;

import br.com.connect.crm.domain.entidade.entity.Entidade;
import br.com.connect.crm.domain.receita.entity.Receita;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosDetalheReceita(
        Long id,
        LocalDate data,
        BigDecimal valor,
        Entidade entidade,
        TipoReceita tipo) implements Serializable {

    public DadosDetalheReceita(Receita receita) {

        this(receita.getId(), receita.getData(), receita.getValor(), receita.getEntidade(), receita.getTipo());
    }

    public void atualizarDados(DadosReceita saldoAtualizado) {
    }
}
