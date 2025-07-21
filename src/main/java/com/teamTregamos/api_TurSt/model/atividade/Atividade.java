package com.teamTregamos.api_TurSt.model.atividade;


import jakarta.persistence.*;
import lombok.*;

@Table(name="tb_atividade")
@Entity(name="Atividade")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private String nome;

    private String descricao;

    private String contato;

    private String foneContato;

    private String regrasUso;

    private String valor;

    private String regrasPagamento;

    private String duracao;

    private String categoria;

    private String localizacao;

    public Atividade(DadosAtividade atividade) {
        this.nome = atividade.nome();
        this.descricao = atividade.descricao();
        this.contato = atividade.contato();
        this.foneContato = atividade.foneContato();
        this.regrasUso = atividade.regrasUso();
        this.valor = atividade.valor();
        this.regrasPagamento = atividade.regrasPagamento();
        this.duracao = atividade.duracao();
        this.categoria = atividade.categoria();
        this.localizacao = atividade.localizacao();
    }
}
