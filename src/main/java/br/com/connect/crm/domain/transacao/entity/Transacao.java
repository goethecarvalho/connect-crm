package br.com.connect.crm.domain.transacao.entity;

import br.com.connect.crm.domain.entidade.entity.Entidade;
import br.com.connect.crm.domain.proposta.entity.Proposta;
import br.com.connect.crm.domain.proposta.vo.DadosProposta;
import br.com.connect.crm.domain.proposta.vo.TipoProposta;
import br.com.connect.crm.domain.transacao.vo.DadosTransacao;
import br.com.connect.crm.domain.transacao.vo.TipoTransacao;
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
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private LocalDate data;
    private BigDecimal valor;
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entidade_id", nullable = false)
    private Entidade entidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proposta_id", nullable = false)
    private Proposta proposta;

    public Transacao(DadosTransacao dados, Entidade entidadeDados, Proposta propostaDados) {
        this.descricao = dados.descricao();
        this.data = dados.data();
        this.valor = dados.valor();
        this.tipo = tipo;
        this.entidade = entidadeDados;
        this.proposta = propostaDados;
    }

    public void atualizarDados(DadosTransacao dados) {
        this.descricao = dados.descricao();
        this.data = dados.data();
        this.valor = dados.valor();
        this.tipo = tipo;
        this.entidade = entidade;
        this.proposta = proposta;
    }

}
