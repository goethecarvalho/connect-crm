package br.com.connect.crm.controller;

import br.com.connect.crm.domain.entidade.service.EntidadeService;
import br.com.connect.crm.domain.entidade.vo.DadosDetalheEntidade;
import br.com.connect.crm.domain.proposta.service.PropostaService;
import br.com.connect.crm.domain.proposta.vo.DadosDetalheProposta;
import br.com.connect.crm.domain.proposta.vo.DadosProposta;
import br.com.connect.crm.domain.saldo.service.SaldoService;
import br.com.connect.crm.domain.saldo.vo.DadosSaldo;
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
@RequestMapping("/api/propostas")
public class PropostaController {

    @Autowired
    private final PropostaService propostaService;

    @Autowired
    private final EntidadeService entidadeService;

    @Autowired
    private final SaldoService saldoService;

    public PropostaController(PropostaService propostaService, EntidadeService entidadeService, SaldoService saldoService) {
        this.propostaService = propostaService;
        this.entidadeService = entidadeService;
        this.saldoService = saldoService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosProposta dados, UriComponentsBuilder uriBuilder){
        DadosDetalheEntidade entidade = entidadeService.detalhar(dados.entidade());
        var dadosPropostaCadastrada = propostaService.cadastrar(dados, entidade);

        DadosSaldo saldo = new DadosSaldo(
                dadosPropostaCadastrada.data(),
                dadosPropostaCadastrada.valor(),
                dadosPropostaCadastrada.id()
        );

        saldoService.cadastrar(saldo, dadosPropostaCadastrada);

        var uri = uriBuilder.path("propostas/{id}").buildAndExpand(dadosPropostaCadastrada.id()).toUri();
        return ResponseEntity.created(uri).body(dadosPropostaCadastrada);
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalheProposta>> listar(@PageableDefault(size = 10, sort = {"descricao"}) Pageable paginacao) {
        var proposta = propostaService.listar(paginacao);
        return ResponseEntity.ok(proposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalheProposta> detalhar(@PathVariable Long id) {
        var proposta = propostaService.detalhar(id);
        return ResponseEntity.ok(proposta);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalheProposta> atualizar(@PathVariable Long id, @RequestBody @Valid DadosProposta dados) {
        var propostaAtualizada = propostaService.atualizar(id, dados);
        return ResponseEntity.ok(propostaAtualizada);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        propostaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
