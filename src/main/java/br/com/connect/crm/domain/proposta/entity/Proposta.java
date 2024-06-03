package br.com.connect.crm.domain.proposta.entity;

import br.com.connect.crm.domain.entidade.entity.Entidade;
import br.com.connect.crm.domain.entidade.vo.DadosDetalheEntidade;
import br.com.connect.crm.domain.proposta.vo.DadosDetalheProposta;
import br.com.connect.crm.domain.proposta.vo.DadosProposta;
import br.com.connect.crm.domain.proposta.vo.StatusProposta;
import br.com.connect.crm.domain.proposta.vo.TipoProposta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "propostas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private LocalDate data;
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private TipoProposta tipo;

    @Enumerated(EnumType.STRING)
    private StatusProposta status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entidade_id")
    private Entidade entidade;

    public Proposta(DadosProposta dados, Entidade entidade) {
        this.descricao = dados.descricao();
        this.data = dados.data();
        this.valor = dados.valor();
        this.tipo = dados.tipo();
        this.entidade = entidade;
        this.status = dados.status();
    }

    public void atualizarDados(DadosProposta dados) {
        this.descricao = dados.descricao();
        this.data = dados.data();
        this.valor = dados.valor();
        this.tipo = dados.tipo();
        this.entidade = entidade;
        this.status = dados.status();
    }

    public Proposta(DadosDetalheProposta dados) {
        this.id = dados.id();
        this.descricao = dados.descricao();
        this.valor = dados.valor();
        this.tipo = dados.tipo();
        this.entidade = entidade;
        this.status = dados.status();
    }

}
