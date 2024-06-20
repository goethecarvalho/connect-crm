package br.com.connect.crm.domain.receita.vo;

import br.com.connect.crm.domain.entidade.vo.DadosDetalheEntidade;
import br.com.connect.crm.domain.projeto.entity.Projeto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosReceita(
        LocalDate data,
        BigDecimal valor,
        Long entidade,
        TipoReceita tipo
) implements Serializable {

    public DadosReceita(Projeto projeto, DadosDetalheEntidade entidade) {
        this(projeto.getData(), projeto.getValor(), entidade.id(), TipoReceita.PROJETO);
    }

}
