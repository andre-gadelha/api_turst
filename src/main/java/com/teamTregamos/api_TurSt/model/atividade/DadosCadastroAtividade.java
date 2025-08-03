package com.teamTregamos.api_TurSt.model.atividade;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroAtividade(
        @NotBlank
        String nome,
        String descricao,
        String contato,
        String foneContato,
        String regrasUso,
        String valor,
        String regrasPagamento,
        String duracao,
        String categoria,
        String localizacao) {
}
