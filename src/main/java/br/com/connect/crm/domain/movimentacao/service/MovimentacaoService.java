package br.com.connect.crm.domain.movimentacao.service;

import br.com.connect.crm.domain.RegraDeNegocioException;
import br.com.connect.crm.domain.entidade.entity.Entidade;
import br.com.connect.crm.domain.entidade.vo.DadosDetalheEntidade;
import br.com.connect.crm.domain.proposta.entity.Proposta;
import br.com.connect.crm.domain.proposta.vo.DadosDetalheProposta;
import br.com.connect.crm.domain.movimentacao.entity.Movimentacao;
import br.com.connect.crm.domain.movimentacao.repository.MovimentacaoRepository;
import br.com.connect.crm.domain.movimentacao.vo.DadosDetalheMovimentacao;
import br.com.connect.crm.domain.movimentacao.vo.DadosMovimentacao;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository repository;

    public MovimentacaoService(MovimentacaoRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "listaMovimentacoes")
    public Page<DadosDetalheMovimentacao> listar(Pageable paginacao) {
        return repository.findAll(paginacao)
                .map(movimentacao -> new DadosDetalheMovimentacao(movimentacao));
    }

    @CacheEvict(value = "listaMovimentacoes", allEntries = true)
    public DadosDetalheMovimentacao cadastrar(DadosMovimentacao dados, DadosDetalheEntidade entidade, DadosDetalheProposta proposta) {
        if (dados.descricao() == null || dados.descricao().isEmpty()) {
            throw new RegraDeNegocioException("A descrição deve estar preenchida!");
        }

        Entidade entidadeDados = new Entidade(entidade);
        Proposta propostaDados = new Proposta(proposta);

        var movimentacao = new Movimentacao(dados, entidadeDados, propostaDados);

        repository.save(movimentacao);

        return new DadosDetalheMovimentacao(movimentacao);
    }

    @CacheEvict(value = "listaMovimentacoes", allEntries = true)
    public DadosDetalheMovimentacao atualizar(Long id, DadosMovimentacao dados) {
        var movimentacao = repository.findById(id).orElseThrow(() -> new RuntimeException("Movimentação não encontrada"));

        DadosMovimentacao transacaoAtualizada = new DadosMovimentacao(
                dados.id(),
                dados.descricao(),
                dados.data(),
                dados.valor(),
                dados.tipo(),
                dados.entidade(),
                dados.proposta()
        );

        movimentacao.atualizarDados(transacaoAtualizada);

        repository.save(movimentacao);

        return new DadosDetalheMovimentacao(movimentacao);
    }

    public DadosDetalheMovimentacao detalhar(Long id) {
        var movimentacao = repository.findById(id).get();
        return new DadosDetalheMovimentacao(movimentacao);
    }

    @CacheEvict(value = "listaMovimentacoes", allEntries = true)
    public void deletar(Long id) {
        var movimentacao = repository.findById(id).orElseThrow(() -> new RuntimeException("Movimentação não encontrada"));
        repository.delete(movimentacao);
    }
}
