package br.com.connect.crm.domain.propostas.service;

import br.com.connect.crm.domain.RegraDeNegocioException;
import br.com.connect.crm.domain.entidades.entity.Entidade;
import br.com.connect.crm.domain.entidades.vo.DadosDetalheEntidade;
import br.com.connect.crm.domain.propostas.entity.Proposta;
import br.com.connect.crm.domain.propostas.repository.PropostaRepository;
import br.com.connect.crm.domain.propostas.vo.DadosDetalheProposta;
import br.com.connect.crm.domain.propostas.vo.DadosProposta;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PropostaService {

    private final PropostaRepository repository;

    public PropostaService(PropostaRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "listaPropostas")
    public Page<DadosDetalheProposta> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosDetalheProposta::new);
    }

    @CacheEvict(value = "listaPropostas", allEntries = true)
    public DadosDetalheProposta cadastrarProposta(DadosProposta dados, DadosDetalheEntidade entidade) {
        if (dados.descricao() == null || dados.descricao().isEmpty()) {
            throw new RegraDeNegocioException("O nome deve estar preenchido!");
        }

        Entidade entidadeDados = new Entidade(entidade);

        var proposta = new Proposta(dados, entidadeDados);

        repository.save(proposta);

        return new DadosDetalheProposta(proposta);
    }

    @CacheEvict(value = "listaPropostas", allEntries = true)
    public DadosDetalheProposta atualizarProposta(Long id, DadosProposta dados) {
        var proposta = repository.findById(id).orElseThrow(() -> new RuntimeException("Proposta não encontrada"));

        DadosProposta propostaAtualizada = new DadosProposta(
                dados.id(),
                dados.descricao(),
                dados.data(),
                dados.valor(),
                dados.entidade(),
                dados.tipo()
        );

        proposta.atualizarDados(propostaAtualizada);

        repository.save(proposta);

        return new DadosDetalheProposta(proposta);
    }

    public DadosDetalheProposta detalhar(Long id) {
        var proposta = repository.findById(id).get();
        return new DadosDetalheProposta(proposta);
    }

    @CacheEvict(value = "listaPropostas", allEntries = true)
    public void deletarProposta(Long id) {
        var proposta = repository.findById(id).orElseThrow(() -> new RuntimeException("Proposta não encontrada"));
        repository.delete(proposta);
    }
}
