package com.teamTregamos.api_TurSt.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DadosCadastroUsuario(
        @NotBlank String nome,
        @NotBlank @Size(min = 11, max= 14) String cpfCnpj,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 8) String senha) {
}
