package br.com.connect.crm.controller;

import br.com.connect.crm.domain.proposta.service.PropostaService;
import br.com.connect.crm.domain.proposta.vo.DadosDetalheProposta;
import br.com.connect.crm.domain.saldo.service.SaldoService;
import br.com.connect.crm.domain.saldo.vo.DadosDetalheSaldo;
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
@RequestMapping("/api/saldos")
public class SaldoController {

    @Autowired
    private final SaldoService saldoService;

    @Autowired
    private final PropostaService propostaService;

    public SaldoController(SaldoService saldoService, PropostaService propostaService) {
        this.saldoService = saldoService;
        this.propostaService = propostaService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosSaldo dados, UriComponentsBuilder uriBuilder){
        DadosDetalheProposta proposta = propostaService.detalhar(dados.proposta());
        var dadosSaldoCadastrado = saldoService.cadastrar(dados, proposta);
        var uri = uriBuilder.path("saldos/{id}").buildAndExpand(dadosSaldoCadastrado.id()).toUri();
        return ResponseEntity.created(uri).body(dadosSaldoCadastrado);
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalheSaldo>> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        var saldo = saldoService.listar(paginacao);
        return ResponseEntity.ok(saldo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalheSaldo> detalhar(@PathVariable Long id) {
        var saldo = saldoService.detalhar(id);
        return ResponseEntity.ok(saldo);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalheSaldo> atualizar(@PathVariable Long id, @RequestBody @Valid DadosSaldo dados) {
        var saldoAtualizada = saldoService.atualizar(id, dados);
        return ResponseEntity.ok(saldoAtualizada);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        saldoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
