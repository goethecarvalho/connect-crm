package br.com.connect.crm.domain.movimentacao.service;

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
import org.hibernate.Hibernate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository repository;

    private final ReceitaRepository receitaRepository;

    public MovimentacaoService(MovimentacaoRepository repository, ReceitaRepository receitaRepository) {
        this.repository = repository;
        this.receitaRepository = receitaRepository;
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "listaMovimentacoes")
    public Page<DadosDetalheMovimentacao> listar(Pageable paginacao) {
        return repository.findAll(paginacao)
                .map(movimentacao -> {
                    // Inicialize as associações necessárias
                    Hibernate.initialize(movimentacao.getEntidade());
                    // Converta para o DTO
                    return new DadosDetalheMovimentacao(movimentacao);
                });
    }

    @CacheEvict(value = "listaMovimentacoes", allEntries = true)
    public DadosDetalheMovimentacao cadastrar(DadosMovimentacao dados, DadosDetalheEntidade entidade, DadosDetalheProposta proposta, DadosDetalheReceita receita) {
        if (dados.descricao() == null || dados.descricao().isEmpty()) {
            throw new RegraDeNegocioException("A descrição deve estar preenchida!");
        }

        Entidade entidadeDados = new Entidade(entidade);
        Proposta propostaDados = null;
        Movimentacao movimentacao;

        if (proposta != null){
            propostaDados = new Proposta(proposta);
            movimentacao = new Movimentacao(dados, propostaDados, entidadeDados);
        }else{
            movimentacao = new Movimentacao(dados, entidadeDados);
        }

        repository.save(movimentacao);

        if (entidadeDados.getTipo().ordinal() == 0 || entidadeDados.getTipo().ordinal() == 3 && propostaDados == null){
            Receita receitaDados = new Receita(
                    dados,
                    entidadeDados
            );

            receitaRepository.save(receitaDados);
        }else{
            DadosDetalheReceita receitaAtualizada = new DadosDetalheReceita(
                    receita.id(),
                    dados.data(),
                    dados.valor(),
                    receita.entidade(),
                    receita.tipo()
            );

            if (dados.tipo().ordinal() == 0){
                receitaRepository.updateDebido(receitaAtualizada.id(), receitaAtualizada.valor());
            }else{
                receitaRepository.updateCredito(receitaAtualizada.id(), receitaAtualizada.valor());
            }
        }

        return new DadosDetalheMovimentacao(movimentacao);
    }

    @CacheEvict(value = "listaMovimentacoes", allEntries = true)
    public DadosDetalheMovimentacao atualizar(Long id, DadosMovimentacao dados) {
        var movimentacao = repository.findById(id).orElseThrow(() -> new RuntimeException("Movimentação não encontrada"));

        DadosMovimentacao movimentacaoAtualizada = new DadosMovimentacao(
                dados.id(),
                dados.descricao(),
                dados.data(),
                dados.valor(),
                dados.entidade(),
                dados.proposta(),
                dados.tipo()
        );

        movimentacao.atualizarDados(movimentacaoAtualizada);

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
