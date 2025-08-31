package com.example.Tozin_Solutions_back_end.V1.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "log_movimentacao")
public class LogMovimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_log")
    private Long id;

    @Column(name = "movimentacao_id", nullable = false)
    private Long movimentacaoId;

    @Column(name = "data_movimentacao", nullable = false)
    private LocalDateTime dataMovimentacao;

    @Column(name = "tipo_peca", length = 20)
    private String tipoPeca;

    @Column(name = "nome_peca", nullable = false)
    private String nomePeca;

    @Column(name = "quantidade_entrada", nullable = false)
    private Integer quantidadeEntrada = 0;

    @Column(name = "quantidade_saida", nullable = false)
    private Integer quantidadeSaida = 0;

    @Column(name = "data_registro", updatable = false)
    private LocalDateTime dataRegistro;

    // Construtores
    public LogMovimentacao() {
        this.dataRegistro = LocalDateTime.now();
    }

    public LogMovimentacao(Long movimentacaoId, LocalDateTime dataMovimentacao,
                           String tipoPeca, String nomePeca,
                           Integer quantidadeEntrada, Integer quantidadeSaida) {
        this();
        this.movimentacaoId = movimentacaoId;
        this.dataMovimentacao = dataMovimentacao;
        this.tipoPeca = tipoPeca;
        this.nomePeca = nomePeca;
        this.quantidadeEntrada = quantidadeEntrada != null ? quantidadeEntrada : 0;
        this.quantidadeSaida = quantidadeSaida != null ? quantidadeSaida : 0;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public Long getMovimentacaoId() {
        return movimentacaoId;
    }

    public void setMovimentacaoId(Long movimentacaoId) {
        this.movimentacaoId = movimentacaoId;
    }

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public String getTipoPeca() {
        return tipoPeca;
    }

    public void setTipoPeca(String tipoPeca) {
        this.tipoPeca = tipoPeca;
    }

    public String getNomePeca() {
        return nomePeca;
    }

    public void setNomePeca(String nomePeca) {
        this.nomePeca = nomePeca;
    }

    public Integer getQuantidadeEntrada() {
        return quantidadeEntrada;
    }

    public void setQuantidadeEntrada(Integer quantidadeEntrada) {
        this.quantidadeEntrada = quantidadeEntrada != null ? quantidadeEntrada : 0;
    }

    public Integer getQuantidadeSaida() {
        return quantidadeSaida;
    }

    public void setQuantidadeSaida(Integer quantidadeSaida) {
        this.quantidadeSaida = quantidadeSaida != null ? quantidadeSaida : 0;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }
}