package br.com.connect.crm.domain.receita.service;

import br.com.connect.crm.domain.RegraDeNegocioException;
import br.com.connect.crm.domain.entidade.entity.Entidade;
import br.com.connect.crm.domain.entidade.vo.DadosDetalheEntidade;
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
        return repository.findAll(paginacao)
                .map(receita -> new DadosDetalheReceita(receita));
    }

    @CacheEvict(value = "listaReceitas", allEntries = true)
    public DadosDetalheReceita cadastrar(DadosReceita dados, DadosDetalheEntidade entidade) {
        if (dados.valor() == null) {
            throw new RegraDeNegocioException("O valor deve estar preenchido!");
        }

        Entidade entidadeDados = new Entidade(entidade);

        var receita = new Receita(dados, entidadeDados);

        repository.save(receita);

        return new DadosDetalheReceita(receita);
    }

    @CacheEvict(value = "listaReceitas", allEntries = true)
    public DadosDetalheReceita atualizar(Long id, DadosReceita dados) {
        var receita = repository.findById(id).orElseThrow(() -> new RuntimeException("Receita não encontrada"));

        DadosReceita receitaAtualizada = new DadosReceita(
                dados.data(),
                dados.valor(),
                dados.entidade(),
                dados.tipo()
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

    public DadosDetalheReceita detalharPorEntidade(Long id) {
        var receita = repository.findByEntidade(id).get();
        return new DadosDetalheReceita(receita);
    }
}
