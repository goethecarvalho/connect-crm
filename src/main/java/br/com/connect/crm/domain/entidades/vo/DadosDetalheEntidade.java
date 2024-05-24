package br.com.connect.crm.domain.entidades.vo;

import br.com.connect.crm.domain.entidades.entity.Entidade;

import java.io.Serializable;

public record DadosDetalheEntidade(
        Long id,
        String nome,
        TipoEntidade tipo) implements Serializable {

    public DadosDetalheEntidade(Entidade entidade) {
        this(entidade.getId(), entidade.getNome(), entidade.getTipo());
    }

}
