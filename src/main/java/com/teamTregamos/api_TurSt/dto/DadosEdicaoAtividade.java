package com.teamTregamos.api_TurSt.dto;

import jakarta.validation.constraints.NotNull;

public record DadosEdicaoAtividade (@NotNull Integer id, String descricao, Integer ativo) {
}
