package br.com.connect.crm.domain.saldo.entity;

import br.com.connect.crm.domain.proposta.entity.Proposta;
import br.com.connect.crm.domain.saldo.vo.DadosSaldo;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "saldos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Saldo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;
    private BigDecimal valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proposta", nullable = false)
    private Proposta proposta;

    public Saldo(DadosSaldo dados, Proposta proposta) {
        this.data = dados.data();
        this.valor = dados.valor();
        this.proposta = proposta;
    }

    public void atualizarDados(DadosSaldo dados) {
        this.id = dados.id();
        this.data = dados.data();
        this.valor = dados.valor();
        this.proposta = proposta;
    }

}
