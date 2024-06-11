package br.com.connect.crm.controller;

import br.com.connect.crm.domain.proposta.service.PropostaService;
import br.com.connect.crm.domain.proposta.vo.DadosDetalheProposta;
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
    private final PropostaService propostaService;

    public ReceitaController(ReceitaService receitaService, PropostaService propostaService) {
        this.receitaService = receitaService;
        this.propostaService = propostaService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosReceita dados, UriComponentsBuilder uriBuilder){
        DadosDetalheProposta proposta = propostaService.detalhar(dados.proposta());
        var dadosReceitaCadastrada = receitaService.cadastrar(dados, proposta);
        var uri = uriBuilder.path("receitas/{id}").buildAndExpand(dadosReceitaCadastrada.id()).toUri();
        return ResponseEntity.created(uri).body(dadosReceitaCadastrada);
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalheReceita>> listar(@PageableDefault(size = 10, sort = {"descricao"}) Pageable paginacao) {
        var receita = receitaService.listar(paginacao);
        return ResponseEntity.ok(receita);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalheReceita> detalhar(@PathVariable Long id) {
        var receita = receitaService.detalhar(id);
        return ResponseEntity.ok(receita);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalheReceita> atualizar(@PathVariable Long id, @RequestBody @Valid DadosReceita dados) {
        var receitaAtualizada = receitaService.atualizar(id, dados);
        return ResponseEntity.ok(receitaAtualizada);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        receitaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
