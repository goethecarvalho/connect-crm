package br.com.connect.crm.controller;

import br.com.connect.crm.domain.usuario.service.UsuarioService;
import br.com.connect.crm.domain.usuario.vo.DadosLoginUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class LoginController {

    @Autowired
    private final UsuarioService service;

    public LoginController(UsuarioService service) {
        this.service = service;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody @Valid DadosLoginUsuario dados, UriComponentsBuilder uriBuilder) {
        Boolean usuarioLogado = service.efetuarLogin(dados);
        if (usuarioLogado) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}
