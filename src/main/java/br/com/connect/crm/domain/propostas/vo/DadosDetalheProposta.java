package br.com.connect.crm.domain.propostas.vo;

import br.com.connect.crm.domain.entidades.entity.Entidade;
import br.com.connect.crm.domain.propostas.entity.Proposta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosDetalheProposta(
        Long id,
        String descricao,
        LocalDate data,
        BigDecimal valor,
        Entidade entidade) implements Serializable {

    public DadosDetalheProposta(Long id, String descricao, LocalDate data, BigDecimal valor, Entidade entidade) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.entidade = entidade;
    }

    public DadosDetalheProposta(Proposta proposta) {

        this(proposta.getId(), proposta.getDescricao(), proposta.getData(), proposta.getValor(), proposta.getEntidade());
    }
}
