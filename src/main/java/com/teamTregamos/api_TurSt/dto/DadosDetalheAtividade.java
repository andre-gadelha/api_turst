package com.teamTregamos.api_TurSt.dto;

import com.teamTregamos.api_TurSt.model.Atividade;

public record DadosDetalheAtividade(
                                    Integer id,
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

    public DadosDetalheAtividade(Atividade atividade) {
        this(atividade.getId(), atividade.getNome(), atividade.getDescricao(), atividade.getContato(), atividade.getFoneContato(),
                atividade.getRegrasUso(), atividade.getValor(), atividade.getRegrasPagamento(), atividade.getDuracao(),
                atividade.getCategoria(), atividade.getLocalizacao());
    }
}


