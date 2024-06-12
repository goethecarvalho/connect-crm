package br.com.connect.crm.domain.proposta.entity;

import br.com.connect.crm.domain.entidade.entity.Entidade;
import br.com.connect.crm.domain.proposta.vo.DadosDetalheProposta;
import br.com.connect.crm.domain.proposta.vo.DadosProposta;
import br.com.connect.crm.domain.proposta.vo.StatusProposta;
import br.com.connect.crm.domain.proposta.vo.TipoProposta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "propostas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Proposta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numero;
    private String descricao;
    private LocalDate data;
    private BigDecimal valor;
    @Enumerated(EnumType.ORDINAL)
    private TipoProposta tipo;
    @Enumerated(EnumType.ORDINAL)
    private StatusProposta status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entidade")
    private Entidade entidade;

    public Proposta(DadosProposta dados, Entidade entidade) {
        this.numero = dados.numero();
        this.descricao = dados.descricao();
        this.data = dados.data();
        this.valor = dados.valor();
        this.tipo = dados.tipo();
        this.entidade = entidade;
        this.status = dados.status();
    }

    public void atualizarDados(DadosProposta dados) {
        this.numero = dados.numero();
        this.descricao = dados.descricao();
        this.data = dados.data();
        this.valor = dados.valor();
        this.tipo = dados.tipo();
        this.entidade = entidade;
        this.status = dados.status();
    }

    public Proposta(DadosDetalheProposta dados) {
        this.id = dados.id();
        this.numero = dados.numero();
        this.descricao = dados.descricao();
        this.valor = dados.valor();
        this.tipo = dados.tipo();
        this.entidade = entidade;
        this.status = dados.status();
    }

}
