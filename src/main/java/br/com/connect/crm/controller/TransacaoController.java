package br.com.connect.crm.controller;

import br.com.connect.crm.domain.entidade.service.EntidadeService;
import br.com.connect.crm.domain.entidade.vo.DadosDetalheEntidade;
import br.com.connect.crm.domain.proposta.service.PropostaService;
import br.com.connect.crm.domain.proposta.vo.DadosDetalheProposta;
import br.com.connect.crm.domain.proposta.vo.DadosProposta;
import br.com.connect.crm.domain.transacao.service.TransacaoService;
import br.com.connect.crm.domain.transacao.vo.DadosDetalheTransacao;
import br.com.connect.crm.domain.transacao.vo.DadosTransacao;
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
@RequestMapping("/api/transacoes")
public class TransacaoController {

    @Autowired
    private final TransacaoService transacaoService;

    @Autowired
    private final EntidadeService entidadeService;

    @Autowired
    private final PropostaService propostaService;

    public TransacaoController(TransacaoService transacaoService, EntidadeService entidadeService, PropostaService propostaService) {
        this.transacaoService = transacaoService;
        this.entidadeService = entidadeService;
        this.propostaService = propostaService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosTransacao dados, UriComponentsBuilder uriBuilder){
        DadosDetalheEntidade entidade = entidadeService.detalhar(dados.entidade());
        DadosDetalheProposta proposta = propostaService.detalhar(dados.proposta());
        var dadosTransacaoCadastrada = transacaoService.cadastrarTransacao(dados, entidade, proposta);
        var uri = uriBuilder.path("transacoes/{id}").buildAndExpand(dadosTransacaoCadastrada.id()).toUri();
        return ResponseEntity.created(uri).body(dadosTransacaoCadastrada);
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalheTransacao>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var transacao = transacaoService.listar(paginacao);
        return ResponseEntity.ok(transacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalheTransacao> detalhar(@PathVariable Long id) {
        var transacao = transacaoService.detalhar(id);
        return ResponseEntity.ok(transacao);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalheTransacao> atualizar(@PathVariable Long id, @RequestBody @Valid DadosTransacao dados) {
        var transacaoAtualizada = transacaoService.atualizarTransacao(id, dados);
        return ResponseEntity.ok(transacaoAtualizada);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        transacaoService.deletarTransacao(id);
        return ResponseEntity.noContent().build();
    }

}
