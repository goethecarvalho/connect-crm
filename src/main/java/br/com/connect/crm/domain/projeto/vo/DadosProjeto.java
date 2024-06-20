package br.com.connect.crm.domain.projeto.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosProjeto(
        Long id,
        Integer numero,
        String descricao,
        LocalDate data,
        BigDecimal valor,
        Long entidade,
        TipoProjeto tipo,
        StatusProjeto status
        ) implements Serializable {

}
