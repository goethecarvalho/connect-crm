package br.com.connect.crm.domain.entidade.vo;

import java.io.Serializable;

public record DadosEntidade(
        Long id,
        String nome,
        TipoEntidade tipo
        ) implements Serializable {
}
