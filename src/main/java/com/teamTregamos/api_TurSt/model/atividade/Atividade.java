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

    public Integer getId() {
        return id;
    }
    public String getNome(){return nome;}
    public String getDescricao(){return descricao;}
    public String getContato(){return contato;}
    public String getFoneContato(){return foneContato;}
    public String getRegrasUso(){return regrasUso;}
    public String getValor(){return valor;}
    public String getRegrasPagamento() {return regrasPagamento;}
    public String getDuracao() {return duracao;}
    public String getCategoria() {return categoria;}
    public String getLocalizacao() {return localizacao;}

    // Construtor padrão (sem argumentos) - ADICIONE ESTE!
    public Atividade() {
        // Opcional: inicializar campos com valores padrão se necessário
    }

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
