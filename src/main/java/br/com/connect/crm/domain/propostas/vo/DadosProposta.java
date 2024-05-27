package br.com.connect.crm.domain.propostas.vo;

import br.com.connect.crm.domain.entidades.entity.Entidade;
import br.com.connect.crm.domain.entidades.vo.TipoEntidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosProposta(
        Long id,
        String descricao,
        LocalDate data,
        BigDecimal valor,
        Long idEntidade
        ) implements Serializable {

    public DadosProposta(String descricao, LocalDate data, BigDecimal valor, Long idEntidade) {
        this(null, descricao, data, valor, idEntidade);
    }

}
