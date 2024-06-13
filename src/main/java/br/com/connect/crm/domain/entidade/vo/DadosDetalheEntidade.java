package br.com.connect.crm.domain.entidade.vo;

import br.com.connect.crm.domain.entidade.entity.Entidade;

import java.io.Serializable;

public record DadosDetalheEntidade (
        Long id,
        String nome,
        TipoEntidade tipo) implements Serializable {

    public DadosDetalheEntidade(Entidade entidade) {
        this(entidade.getId(), entidade.getNome(), entidade.getTipo());
    }

}
