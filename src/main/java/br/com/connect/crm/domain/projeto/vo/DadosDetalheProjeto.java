package br.com.connect.crm.domain.projeto.vo;

import br.com.connect.crm.domain.entidade.entity.Entidade;
import br.com.connect.crm.domain.projeto.entity.Projeto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosDetalheProjeto(
        Long id,
        Integer numero,
        String descricao,
        LocalDate data,
        BigDecimal valor,
        Entidade entidade,
        TipoProjeto tipo,
        StatusProjeto status) implements Serializable {

    public DadosDetalheProjeto(Projeto projeto) {
        this(
                projeto.getId(),
                projeto.getNumero(),
                projeto.getDescricao(),
                projeto.getData(),
                projeto.getValor(),
                projeto.getEntidade(),
                projeto.getTipo(),
                projeto.getStatus()
        );
    }
}
