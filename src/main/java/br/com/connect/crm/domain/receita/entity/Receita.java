package br.com.connect.crm.domain.receita.entity;

import br.com.connect.crm.domain.entidade.entity.Entidade;
import br.com.connect.crm.domain.proposta.entity.Proposta;
import br.com.connect.crm.domain.receita.vo.DadosReceita;
import br.com.connect.crm.domain.receita.vo.TipoReceita;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "receitas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Receita implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;
    private BigDecimal valor;

    @Enumerated(EnumType.ORDINAL)
    private TipoReceita tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entidade", nullable = false)
    private Entidade entidade;

    @Transient
    private Proposta proposta;

    public Receita(DadosReceita dados, Entidade entidade) {
        this.data = dados.data();
        this.valor = dados.valor();
        if (entidade.getTipo().ordinal() == 0){
            this.tipo = TipoReceita.INVESTIMENTO;
        } else if (entidade.getTipo().ordinal() == 0){
            this.tipo = TipoReceita.PROJETO;
        }
        this.entidade = entidade;
    }

    public Receita(Proposta proposta, Entidade entidade) {
        this.data = proposta.getData();
        this.valor = proposta.getValor();
        if (entidade.getTipo().ordinal() == 0){
            this.tipo = TipoReceita.INVESTIMENTO;
        } else if (entidade.getTipo().ordinal() == 4){
            this.tipo = TipoReceita.PROJETO;
        }
        this.entidade = entidade;
    }

    public void atualizarDados(DadosReceita dados) {
        this.data = dados.data();
        this.valor = dados.valor();
        this.entidade = entidade;
    }

}
