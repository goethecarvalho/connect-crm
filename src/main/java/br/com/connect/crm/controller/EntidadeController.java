package br.com.connect.crm.controller;

import br.com.connect.crm.domain.entidades.service.EntidadeService;
import br.com.connect.crm.domain.entidades.vo.DadosDetalheEntidade;
import br.com.connect.crm.domain.entidades.vo.DadosEntidade;
import br.com.connect.crm.domain.usuario.service.UsuarioService;
import br.com.connect.crm.domain.usuario.vo.DadosDetalheUsuario;
import br.com.connect.crm.domain.usuario.vo.DadosUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("entidades")
public class EntidadeController {

    @Autowired
    private final EntidadeService service;

    public EntidadeController(EntidadeService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosEntidade dados, UriComponentsBuilder uriBuilder){

        var dadosEntidadeCadastrada = service.cadastrarEntidade(dados);
        var uri = uriBuilder.path("entidades/{id}").buildAndExpand(dadosEntidadeCadastrada.id()).toUri();
        return ResponseEntity.created(uri).body(dadosEntidadeCadastrada);
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalheEntidade>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var entidade = service.listar(paginacao);
        return ResponseEntity.ok(entidade);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalheEntidade> detalhar(@PathVariable Long id) {
        var entidade = service.detalhar(id);
        return ResponseEntity.ok(entidade);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalheEntidade> atualizar(@PathVariable Long id, @RequestBody @Valid DadosEntidade dados) {
        var entidadeAtualizada = service.atualizarEntidade(id, dados);
        return ResponseEntity.ok(entidadeAtualizada);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletarEntidade(id);
        return ResponseEntity.noContent().build();
    }

}
