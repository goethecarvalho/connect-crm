package br.com.connect.crm.domain.projeto.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosProjeto(
        Long id,

        @JsonProperty("numero")
        Integer numero,

        @JsonProperty("descricao")
        String descricao,

        @JsonProperty("data")
        LocalDate data,

        @JsonProperty("valor")
        BigDecimal valor,

        @JsonProperty("Entidade")
        Long entidade,

        @JsonProperty("TipoProjeto")
        TipoProjeto tipo,

        @JsonProperty("StatusProjeto")
        StatusProjeto status
        ) implements Serializable {

}
