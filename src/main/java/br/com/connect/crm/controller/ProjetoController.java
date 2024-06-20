package br.com.connect.crm.controller;

import br.com.connect.crm.domain.entidade.service.EntidadeService;
import br.com.connect.crm.domain.entidade.vo.DadosDetalheEntidade;
import br.com.connect.crm.domain.projeto.service.ProjetoService;
import br.com.connect.crm.domain.projeto.vo.DadosDetalheProjeto;
import br.com.connect.crm.domain.projeto.vo.DadosProjeto;
import br.com.connect.crm.domain.receita.service.ReceitaService;
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
@RequestMapping("/api/projetos")
public class ProjetoController {

    @Autowired
    private final ProjetoService projetoService;

    @Autowired
    private final EntidadeService entidadeService;

    @Autowired
    private final ReceitaService receitaService;

    public ProjetoController(ProjetoService projetoService, EntidadeService entidadeService, ReceitaService receitaService) {
        this.projetoService = projetoService;
        this.entidadeService = entidadeService;
        this.receitaService = receitaService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosProjeto dados, UriComponentsBuilder uriBuilder){

        DadosDetalheEntidade entidade = entidadeService.detalhar(dados.entidade());
        var dadosProjetoCadastrado = projetoService.cadastrar(dados, entidade);

        var uri = uriBuilder.path("projetos/{id}").buildAndExpand(dadosProjetoCadastrado.id()).toUri();
        return ResponseEntity.created(uri).body(dadosProjetoCadastrado);
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalheProjeto>> listar(@PageableDefault(size = 10, sort = {"descricao"}) Pageable paginacao) {
        var projeto = projetoService.listar(paginacao);
        return ResponseEntity.ok(projeto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalheProjeto> detalhar(@PathVariable Long id) {
        var projeto = projetoService.detalhar(id);
        return ResponseEntity.ok(projeto);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalheProjeto> atualizar(@PathVariable Long id, @RequestBody @Valid DadosProjeto dados) {
        var projetoAtualizada = projetoService.atualizar(id, dados);
        return ResponseEntity.ok(projetoAtualizada);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        projetoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
