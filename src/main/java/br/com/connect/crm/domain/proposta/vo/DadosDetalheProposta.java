package br.com.connect.crm.domain.proposta.vo;

import br.com.connect.crm.domain.entidade.entity.Entidade;
import br.com.connect.crm.domain.proposta.entity.Proposta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosDetalheProposta(
        Long id,
        Integer numero,
        String descricao,
        LocalDate data,
        BigDecimal valor,
        Entidade entidade,
        TipoProposta tipo,
        StatusProposta status) implements Serializable {

    public DadosDetalheProposta(Proposta proposta) {
        this(
                proposta.getId(),
                proposta.getNumero(),
                proposta.getDescricao(),
                proposta.getData(),
                proposta.getValor(),
                proposta.getEntidade(),
                proposta.getTipo(),
                proposta.getStatus()
        );
    }
}
