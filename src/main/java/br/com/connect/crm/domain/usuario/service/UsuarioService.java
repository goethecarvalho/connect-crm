package br.com.connect.crm.domain.usuario.service;

import br.com.connect.crm.domain.RegraDeNegocioException;
import br.com.connect.crm.domain.usuario.entity.Usuario;
import br.com.connect.crm.domain.usuario.repository.UsuarioRepository;
import br.com.connect.crm.domain.usuario.vo.DadosDetalheUsuario;
import br.com.connect.crm.domain.usuario.vo.DadosLoginUsuario;
import br.com.connect.crm.domain.usuario.vo.DadosUsuario;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Page<DadosDetalheUsuario> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosDetalheUsuario::new);
    }

    public DadosDetalheUsuario cadastrarUsuario(DadosUsuario dados) {
        if (dados.login() == null || dados.login().isEmpty()) {
            throw new RegraDeNegocioException("O login deve estar preenchido!");
        }
        if (dados.senha() == null || dados.senha().isEmpty()) {
            throw new RegraDeNegocioException("A senha deve estar preenchida!");
        }

        var usuario = new Usuario(dados);

        repository.save(usuario);

        return new DadosDetalheUsuario(usuario);
    }

    public DadosDetalheUsuario atualizarUsuario(Long id, DadosUsuario dados) {
        var usuario = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        DadosUsuario usuarioAtualizado = new DadosUsuario(
                dados.id(),
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

    public void deletarUsuario(Long id) {
        var usuario = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        repository.delete(usuario);
    }

    public Boolean efetuarLogin(@Valid DadosLoginUsuario dados) {
        String login = dados.login();
        String senha = dados.senha();
        return repository.findLoginUsuario(login, senha);
    }
}
