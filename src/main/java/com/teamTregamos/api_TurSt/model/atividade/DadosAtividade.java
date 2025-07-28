package com.teamTregamos.api_TurSt.model.atividade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtividade(
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
