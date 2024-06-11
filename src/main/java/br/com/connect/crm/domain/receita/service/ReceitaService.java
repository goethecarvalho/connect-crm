package br.com.connect.crm.domain.receita.service;

import br.com.connect.crm.domain.RegraDeNegocioException;
import br.com.connect.crm.domain.entidade.entity.Entidade;
import br.com.connect.crm.domain.entidade.vo.DadosDetalheEntidade;
import br.com.connect.crm.domain.movimentacao.entity.Movimentacao;
import br.com.connect.crm.domain.movimentacao.repository.MovimentacaoRepository;
import br.com.connect.crm.domain.movimentacao.vo.DadosDetalheMovimentacao;
import br.com.connect.crm.domain.movimentacao.vo.DadosMovimentacao;
import br.com.connect.crm.domain.proposta.entity.Proposta;
import br.com.connect.crm.domain.proposta.vo.DadosDetalheProposta;
import br.com.connect.crm.domain.receita.entity.Receita;
import br.com.connect.crm.domain.receita.repository.ReceitaRepository;
import br.com.connect.crm.domain.receita.vo.DadosDetalheReceita;
import br.com.connect.crm.domain.receita.vo.DadosReceita;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReceitaService {

    private final ReceitaRepository repository;

    public ReceitaService(ReceitaRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "listaReceitas")
    public Page<DadosDetalheReceita> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosDetalheReceita::new);
    }

    @CacheEvict(value = "listaReceitas", allEntries = true)
    public DadosDetalheReceita cadastrar(DadosReceita dados, DadosDetalheProposta proposta) {
        if (dados.descricao() == null || dados.descricao().isEmpty()) {
            throw new RegraDeNegocioException("A descrição deve estar preenchida!");
        }

        Proposta propostaDados = new Proposta(proposta);

        var transacao = new Receita(dados, propostaDados);

        repository.save(transacao);

        return new DadosDetalheReceita(transacao);
    }

    @CacheEvict(value = "listaReceitas", allEntries = true)
    public DadosDetalheReceita atualizar(Long id, DadosReceita dados) {
        var receita = repository.findById(id).orElseThrow(() -> new RuntimeException("Receita não encontrada"));

        DadosReceita receitaAtualizada = new DadosReceita(
                dados.id(),
                dados.descricao(),
                dados.data(),
                dados.valor(),
                dados.proposta()
        );

        receita.atualizarDados(receitaAtualizada);

        repository.save(receita);

        return new DadosDetalheReceita(receita);
    }

    public DadosDetalheReceita detalhar(Long id) {
        var receita = repository.findById(id).get();
        return new DadosDetalheReceita(receita);
    }

    @CacheEvict(value = "listaReceitas", allEntries = true)
    public void deletar(Long id) {
        var receita = repository.findById(id).orElseThrow(() -> new RuntimeException("Receita não encontrada"));
        repository.delete(receita);
    }
}
