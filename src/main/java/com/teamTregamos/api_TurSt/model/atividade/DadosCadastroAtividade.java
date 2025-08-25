package com.teamTregamos.api_TurSt.model.atividade;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


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
