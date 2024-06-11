package br.com.connect.crm.controller;

import br.com.connect.crm.domain.entidade.service.EntidadeService;
import br.com.connect.crm.domain.entidade.vo.DadosDetalheEntidade;
import br.com.connect.crm.domain.proposta.service.PropostaService;
import br.com.connect.crm.domain.proposta.vo.DadosDetalheProposta;
import br.com.connect.crm.domain.movimentacao.service.MovimentacaoService;
import br.com.connect.crm.domain.movimentacao.vo.DadosDetalheMovimentacao;
import br.com.connect.crm.domain.movimentacao.vo.DadosMovimentacao;
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
@RequestMapping("/api/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private final MovimentacaoService movimentacaoService;

    @Autowired
    private final EntidadeService entidadeService;

    @Autowired
    private final PropostaService propostaService;

    public MovimentacaoController(MovimentacaoService movimentacaoService, EntidadeService entidadeService, PropostaService propostaService) {
        this.movimentacaoService = movimentacaoService;
        this.entidadeService = entidadeService;
        this.propostaService = propostaService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosMovimentacao dados, UriComponentsBuilder uriBuilder){
        DadosDetalheEntidade entidade = entidadeService.detalhar(dados.entidade());
        DadosDetalheProposta proposta = propostaService.detalhar(dados.proposta());
        var dadosMovimentacaoCadastrada = movimentacaoService.cadastrar(dados, entidade, proposta);
        var uri = uriBuilder.path("transacoes/{id}").buildAndExpand(dadosMovimentacaoCadastrada.id()).toUri();
        return ResponseEntity.created(uri).body(dadosMovimentacaoCadastrada);
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalheMovimentacao>> listar(@PageableDefault(size = 10, sort = {"descricao"}) Pageable paginacao) {
        var movimentacao = movimentacaoService.listar(paginacao);
        return ResponseEntity.ok(movimentacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalheMovimentacao> detalhar(@PathVariable Long id) {
        var movimentacao = movimentacaoService.detalhar(id);
        return ResponseEntity.ok(movimentacao);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalheMovimentacao> atualizar(@PathVariable Long id, @RequestBody @Valid DadosMovimentacao dados) {
        var movimentacaoAtualizada = movimentacaoService.atualizar(id, dados);
        return ResponseEntity.ok(movimentacaoAtualizada);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        movimentacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
