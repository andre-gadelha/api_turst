package com.teamTregamos.api_TurSt.controller;

import com.teamTregamos.api_TurSt.dto.DadosCadastroUsuario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario requestUsuario){

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity listar(){

        return ResponseEntity.ok("Consultando...");
    }
}
