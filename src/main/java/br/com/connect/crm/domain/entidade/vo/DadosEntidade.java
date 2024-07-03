package br.com.connect.crm.domain.entidade.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record DadosEntidade(
        Long id,

        @JsonProperty("nome")
        String nome,

        @JsonProperty("TipoEntidade")
        TipoEntidade tipo
        ) implements Serializable {
}
