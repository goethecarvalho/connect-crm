package br.com.connect.crm.domain.usuario.service;

import br.com.connect.crm.domain.RegraDeNegocioException;
import br.com.connect.crm.domain.usuario.entity.Usuario;
import br.com.connect.crm.domain.usuario.repository.UsuarioRepository;
import br.com.connect.crm.domain.usuario.vo.DadosDetalheUsuario;
import br.com.connect.crm.domain.usuario.vo.DadosUsuario;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "listaUsuarios")
    public Page<DadosDetalheUsuario> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosDetalheUsuario::new);
    }


    @CacheEvict(value = "listaUsuarios", allEntries = true)
    public DadosDetalheUsuario cadastrarUsuario(DadosUsuario dados) {
        if (dados.nome() == null || dados.nome().isEmpty()) {
            throw new RegraDeNegocioException("O nome deve estar preenchido!");
        }

        var usuario = new Usuario(dados);

        repository.save(usuario);

        return new DadosDetalheUsuario(usuario);
    }

    @CacheEvict(value = "listaUsuarios", allEntries = true)
    public DadosDetalheUsuario atualizarUsuario(Long id, DadosUsuario dados) {
        var usuario = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        DadosUsuario usuarioAtualizado = new DadosUsuario(
                dados.id(),
                dados.nome(),
                dados.login(),
                dados.senha()
        );

        usuario.atualizarDados(usuarioAtualizado);

        repository.save(usuario);

        return new DadosDetalheUsuario(usuario);
    }

    public DadosDetalheUsuario detalhar(Long id) {
        var usuario = repository.findById(id).get();
        return new DadosDetalheUsuario(usuario);
    }

    @CacheEvict(value = "listaUsuarios", allEntries = true)
    public void deletarUsuario(Long id) {
        var usuario = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        repository.delete(usuario);
    }
}
