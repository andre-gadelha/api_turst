package com.teamTregamos.api_TurSt.controller;

import com.teamTregamos.api_TurSt.dto.DadosCadastroUsuario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario requestUsuario){
        return ResponseEntity.ok(requestUsuario);
    }

    @GetMapping
    @SecurityRequirement(name = "bearerAuth")//indica que o endpoint requer autenticação via token Bearer(Por padrão o Spring Security não exige autenticação para endpoints que contenham /usuario ou semelhantes, então é necessário configurar isso)
    public ResponseEntity listar(){
        return ResponseEntity.ok().build();
    }

}
