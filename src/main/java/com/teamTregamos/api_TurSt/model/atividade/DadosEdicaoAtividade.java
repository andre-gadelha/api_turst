package com.teamTregamos.api_TurSt.model.atividade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosEdicaoAtividade (@NotNull Integer id, String descricao, Integer ativo) {
}
