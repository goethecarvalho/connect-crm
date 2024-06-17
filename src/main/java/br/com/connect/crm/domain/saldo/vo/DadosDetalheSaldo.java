package br.com.connect.crm.domain.saldo.vo;

import br.com.connect.crm.domain.proposta.entity.Proposta;
import br.com.connect.crm.domain.saldo.entity.Saldo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosDetalheSaldo(
        Long id,
        LocalDate data,
        BigDecimal valor,
        Proposta proposta) implements Serializable {

    public DadosDetalheSaldo(Saldo saldo) {

        this(saldo.getId(), saldo.getData(), saldo.getValor(), saldo.getProposta());
    }

    public void atualizarDados(DadosSaldo saldoAtualizado) {
    }
}
