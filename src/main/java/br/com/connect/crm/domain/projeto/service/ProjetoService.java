package br.com.connect.crm.domain.projeto.service;

import br.com.connect.crm.domain.RegraDeNegocioException;
import br.com.connect.crm.domain.entidade.entity.Entidade;
import br.com.connect.crm.domain.entidade.vo.DadosDetalheEntidade;
import br.com.connect.crm.domain.projeto.entity.Projeto;
import br.com.connect.crm.domain.projeto.repository.ProjetoRepository;
import br.com.connect.crm.domain.projeto.vo.DadosDetalheProjeto;
import br.com.connect.crm.domain.projeto.vo.DadosProjeto;
import br.com.connect.crm.domain.receita.entity.Receita;
import br.com.connect.crm.domain.receita.repository.ReceitaRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProjetoService {

    private final ProjetoRepository repository;

    private final ReceitaRepository receitaRepository;

    public ProjetoService(ProjetoRepository repository, ReceitaRepository receitaRepository) {
        this.repository = repository;
        this.receitaRepository = receitaRepository;
    }

    @Cacheable(value = "listaProjetos")
    public Page<DadosDetalheProjeto> listar(Pageable paginacao) {
        return repository.findAll(paginacao)
                .map(projeto -> new DadosDetalheProjeto(projeto));
    }

    @CacheEvict(value = "listaProjetos", allEntries = true)
    public DadosDetalheProjeto cadastrar(DadosProjeto dados, DadosDetalheEntidade entidade) {
        if (dados.descricao() == null || dados.descricao().isEmpty()) {
            throw new RegraDeNegocioException("A descrição deve estar preenchida!");
        }

        Entidade entidadeDados = new Entidade(entidade);

        var projeto = new Projeto(dados, entidadeDados);

        repository.save(projeto);

        Receita receita = new Receita(
                projeto,
                entidadeDados
        );

        receitaRepository.save(receita);

        return new DadosDetalheProjeto(projeto);
    }

    @CacheEvict(value = "listaProjetos", allEntries = true)
    public DadosDetalheProjeto atualizar(Long id, DadosProjeto dados) {
        var projeto = repository.findById(id).orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        DadosProjeto projetoAtualizado = new DadosProjeto(
                dados.id(),
                dados.numero(),
                dados.descricao(),
                dados.data(),
                dados.valor(),
                dados.entidade(),
                dados.tipo(),
                dados.status()
        );

        projeto.atualizarDados(projetoAtualizado);

        repository.save(projeto);

        return new DadosDetalheProjeto(projeto);
    }

    public DadosDetalheProjeto detalhar(Long id) {
        var projeto = repository.findById(id).get();
        return new DadosDetalheProjeto(projeto);
    }

    @CacheEvict(value = "listaProjetos", allEntries = true)
    public void deletar(Long id) {
        var projeto = repository.findById(id).orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
        repository.delete(projeto);
    }
}
