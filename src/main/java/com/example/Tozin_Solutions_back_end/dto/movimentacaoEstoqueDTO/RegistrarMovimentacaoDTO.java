package com.example.Tozin_Solutions_back_end.dto.movimentacaoEstoqueDTO;

import com.example.Tozin_Solutions_back_end.model.Peca;

public class RegistrarMovimentacaoDTO {
    private Integer quantidadeSaida;
    private Integer quantidadeEntrada;
    private Peca peca;

    public RegistrarMovimentacaoDTO(Integer quantidadeSaida, Integer quantidadeEntrada, Peca peca) {
        this.quantidadeSaida = quantidadeSaida;
        this.quantidadeEntrada = quantidadeEntrada;
        this.peca = peca;
    }

    public Integer getQuantidadeSaida() {
        return quantidadeSaida;
    }

    public Integer getQuantidadeEntrada() {
        return quantidadeEntrada;
    }

    public Peca getPeca() {
        return peca;
    }
}
