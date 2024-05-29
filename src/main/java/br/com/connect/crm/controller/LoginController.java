package br.com.connect.crm.controller;

import br.com.connect.crm.domain.usuario.vo.DadosLogin;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class LoginController {

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<String> login(@RequestBody @Valid DadosLogin dados, UriComponentsBuilder uriBuilder) {
        if ("admin".equals(dados.getUsername()) && "admin".equals(dados.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
