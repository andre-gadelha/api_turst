package com.teamTregamos.api_TurSt.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(@NotBlank String nome, @NotBlank String cpf, @NotBlank String email, @NotBlank String senha) {
}
