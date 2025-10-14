package com.example.Tozin_Solutions_back_end.V2.core.domain.logMovimentacaoDomain;

import java.time.LocalDateTime;

public class LogMovimentacao {
    private Long id;
    private Long movimentacaoId;
    private LocalDateTime dataMovimentacao;
    private String tipoPeca;
    private String nomePeca;
    private Integer quantidadeEntrada;
    private Integer quantidadeSaida;
    private LocalDateTime dataRegistro;

    // Construtor para reconstituição do banco
    public LogMovimentacao(Long id, Long movimentacaoId, LocalDateTime dataMovimentacao,
                           String tipoPeca, String nomePeca,
                           Integer quantidadeEntrada, Integer quantidadeSaida,
                           LocalDateTime dataRegistro) {
        this.id = id;
        this.movimentacaoId = movimentacaoId;
        this.dataMovimentacao = dataMovimentacao;
        this.tipoPeca = tipoPeca;
        this.nomePeca = nomePeca;
        this.quantidadeEntrada = quantidadeEntrada != null ? quantidadeEntrada : 0;
        this.quantidadeSaida = quantidadeSaida != null ? quantidadeSaida : 0;
        this.dataRegistro = dataRegistro;
    }

    // Getters (sem setters - imutável)
    public Long getId() { return id; }
    public Long getMovimentacaoId() { return movimentacaoId; }
    public LocalDateTime getDataMovimentacao() { return dataMovimentacao; }
    public String getTipoPeca() { return tipoPeca; }
    public String getNomePeca() { return nomePeca; }
    public Integer getQuantidadeEntrada() { return quantidadeEntrada; }
    public Integer getQuantidadeSaida() { return quantidadeSaida; }
    public LocalDateTime getDataRegistro() { return dataRegistro; }
}
