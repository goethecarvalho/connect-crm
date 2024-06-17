package br.com.connect.crm.domain.saldo.service;

import br.com.connect.crm.domain.RegraDeNegocioException;
import br.com.connect.crm.domain.proposta.entity.Proposta;
import br.com.connect.crm.domain.proposta.vo.DadosDetalheProposta;
import br.com.connect.crm.domain.saldo.entity.Saldo;
import br.com.connect.crm.domain.saldo.repository.SaldoRepository;
import br.com.connect.crm.domain.saldo.vo.DadosDetalheSaldo;
import br.com.connect.crm.domain.saldo.vo.DadosSaldo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SaldoService {

    private final SaldoRepository repository;

    public SaldoService(SaldoRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "listaSaldos")
    public Page<DadosDetalheSaldo> listar(Pageable paginacao) {
        return repository.findAll(paginacao)
                .map(receita -> new DadosDetalheSaldo(receita));
    }

    @CacheEvict(value = "listaSaldos", allEntries = true)
    public DadosDetalheSaldo cadastrar(DadosSaldo dados, DadosDetalheProposta proposta) {
        if (dados.valor() == null) {
            throw new RegraDeNegocioException("O valor deve estar preenchido!");
        }

        Proposta propostaDados = new Proposta(proposta);

        var receita = new Saldo(dados, propostaDados);

        repository.save(receita);

        return new DadosDetalheSaldo(receita);
    }

    @CacheEvict(value = "listaSaldos", allEntries = true)
    public DadosDetalheSaldo atualizar(Long id, DadosSaldo dados) {
        var saldo = repository.findById(id).orElseThrow(() -> new RuntimeException("Saldo não encontrado"));

        DadosSaldo saldoAtualizado = new DadosSaldo(
                id,
                dados.data(),
                dados.valor(),
                dados.proposta()
        );

        saldo.atualizarDados(saldoAtualizado);

        repository.save(saldo);

        return new DadosDetalheSaldo(saldo);
    }

    public DadosDetalheSaldo detalhar(Long id) {
        var saldo = repository.findById(id).get();
        return new DadosDetalheSaldo(saldo);
    }

    @CacheEvict(value = "listaSaldos", allEntries = true)
    public void deletar(Long id) {
        var saldo = repository.findById(id).orElseThrow(() -> new RuntimeException("Saldo não encontrado"));
        repository.delete(saldo);
    }

    public DadosDetalheSaldo detalharPorProposta(Long proposta) {
        var saldo = repository.findByIdProposta(proposta).get();
        return new DadosDetalheSaldo(saldo);
    }
}
