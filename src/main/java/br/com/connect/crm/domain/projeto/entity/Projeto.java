package br.com.connect.crm.domain.projeto.entity;

import br.com.connect.crm.domain.entidade.entity.Entidade;
import br.com.connect.crm.domain.projeto.vo.DadosDetalheProjeto;
import br.com.connect.crm.domain.projeto.vo.DadosProjeto;
import br.com.connect.crm.domain.projeto.vo.StatusProjeto;
import br.com.connect.crm.domain.projeto.vo.TipoProjeto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "projetos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Projeto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numero;
    private String descricao;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;
    private BigDecimal valor;
    @Enumerated(EnumType.ORDINAL)
    private TipoProjeto tipo;
    @Enumerated(EnumType.ORDINAL)
    private StatusProjeto status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entidade")
    private Entidade entidade;

    public Projeto(DadosProjeto dados, Entidade entidade) {
        this.numero = dados.numero();
        this.descricao = dados.descricao();
        this.data = dados.data();
        this.valor = dados.valor();
        this.tipo = dados.tipo();
        this.entidade = entidade;
        this.status = dados.status();
    }

    public void atualizarDados(DadosProjeto dados) {
        this.numero = dados.numero();
        this.descricao = dados.descricao();
        this.data = dados.data();
        this.valor = dados.valor();
        this.tipo = dados.tipo();
        this.entidade = entidade;
        this.status = dados.status();
    }

    public Projeto(DadosDetalheProjeto dados) {
        this.id = dados.id();
        this.numero = dados.numero();
        this.descricao = dados.descricao();
        this.valor = dados.valor();
        this.tipo = dados.tipo();
        this.entidade = entidade;
        this.status = dados.status();
    }

}
