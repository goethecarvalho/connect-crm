package br.com.connect.crm.controller;

import br.com.connect.crm.domain.entidade.service.EntidadeService;
import br.com.connect.crm.domain.entidade.vo.DadosDetalheEntidade;
import br.com.connect.crm.domain.receita.service.ReceitaService;
import br.com.connect.crm.domain.receita.vo.DadosDetalheReceita;
import br.com.connect.crm.domain.receita.vo.DadosReceita;
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
@RequestMapping("/api/receitas")
public class ReceitaController {

    @Autowired
    private final ReceitaService receitaService;

    @Autowired
    private final EntidadeService entidadeService;

    public ReceitaController(ReceitaService receitaService, EntidadeService entidadeService) {
        this.receitaService = receitaService;
        this.entidadeService = entidadeService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosReceita dados, UriComponentsBuilder uriBuilder){
        DadosDetalheEntidade entidade = entidadeService.detalhar(dados.entidade());
        var dadosReceitaCadastrada = receitaService.cadastrar(dados, entidade);
        var uri = uriBuilder.path("receitas/{id}").buildAndExpand(dadosReceitaCadastrada.id()).toUri();
        return ResponseEntity.created(uri).body(dadosReceitaCadastrada);
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalheReceita>> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        var saldo = receitaService.listar(paginacao);
        return ResponseEntity.ok(saldo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalheReceita> detalhar(@PathVariable Long id) {
        var saldo = receitaService.detalhar(id);
        return ResponseEntity.ok(saldo);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalheReceita> atualizar(@PathVariable Long id, @RequestBody @Valid DadosReceita dados) {
        var saldoAtualizada = receitaService.atualizar(id, dados);
        return ResponseEntity.ok(saldoAtualizada);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        receitaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
