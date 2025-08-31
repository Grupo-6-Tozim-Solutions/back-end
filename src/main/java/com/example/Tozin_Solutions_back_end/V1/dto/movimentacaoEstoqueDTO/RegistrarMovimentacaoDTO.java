package com.example.Tozin_Solutions_back_end.V1.dto.movimentacaoEstoqueDTO;

import com.example.Tozin_Solutions_back_end.V1.model.Peca;

public class RegistrarMovimentacaoDTO {
    private Double quantidadeSaida;
    private Double quantidadeEntrada;
    private Peca peca;

    public RegistrarMovimentacaoDTO(Double quantidadeSaida, Double quantidadeEntrada, Peca peca) {
        this.quantidadeSaida = quantidadeSaida;
        this.quantidadeEntrada = quantidadeEntrada;
        this.peca = peca;
    }

    public Double getQuantidadeSaida() {
        return quantidadeSaida;
    }

    public Double getQuantidadeEntrada() {
        return quantidadeEntrada;
    }

    public Peca getPeca() {
        return peca;
    }
}
