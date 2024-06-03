package br.com.connect.crm.domain.transacao.service;

import br.com.connect.crm.domain.RegraDeNegocioException;
import br.com.connect.crm.domain.entidade.entity.Entidade;
import br.com.connect.crm.domain.entidade.vo.DadosDetalheEntidade;
import br.com.connect.crm.domain.proposta.entity.Proposta;
import br.com.connect.crm.domain.proposta.vo.DadosDetalheProposta;
import br.com.connect.crm.domain.transacao.entity.Transacao;
import br.com.connect.crm.domain.transacao.repository.TransacaoRepository;
import br.com.connect.crm.domain.transacao.vo.DadosDetalheTransacao;
import br.com.connect.crm.domain.transacao.vo.DadosTransacao;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

    private final TransacaoRepository repository;

    public TransacaoService(TransacaoRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "listaTransacoes")
    public Page<DadosDetalheTransacao> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosDetalheTransacao::new);
    }

    @CacheEvict(value = "listaTransacoes", allEntries = true)
    public DadosDetalheTransacao cadastrarTransacao(DadosTransacao dados, DadosDetalheEntidade entidade, DadosDetalheProposta proposta) {
        if (dados.descricao() == null || dados.descricao().isEmpty()) {
            throw new RegraDeNegocioException("A descrição deve estar preenchida!");
        }

        Entidade entidadeDados = new Entidade(entidade);
        Proposta propostaDados = new Proposta(proposta);

        var transacao = new Transacao(dados, entidadeDados, propostaDados);

        repository.save(transacao);

        return new DadosDetalheTransacao(transacao);
    }

    @CacheEvict(value = "listaTransacoes", allEntries = true)
    public DadosDetalheTransacao atualizarTransacao(Long id, DadosTransacao dados) {
        var transacao = repository.findById(id).orElseThrow(() -> new RuntimeException("Transação não encontrada"));

        DadosTransacao transacaoAtualizada = new DadosTransacao(
                dados.id(),
                dados.descricao(),
                dados.data(),
                dados.valor(),
                dados.tipo(),
                dados.entidade(),
                dados.proposta()
        );

        transacao.atualizarDados(transacaoAtualizada);

        repository.save(transacao);

        return new DadosDetalheTransacao(transacao);
    }

    public DadosDetalheTransacao detalhar(Long id) {
        var transacao = repository.findById(id).get();
        return new DadosDetalheTransacao(transacao);
    }

    @CacheEvict(value = "listaTransacaos", allEntries = true)
    public void deletarTransacao(Long id) {
        var transacao = repository.findById(id).orElseThrow(() -> new RuntimeException("Transacao não encontrada"));
        repository.delete(transacao);
    }
}
