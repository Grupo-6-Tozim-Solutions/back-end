package com.example.Tozin_Solutions_back_end.V1.dto.pecaDTO;

public class AtualizarPecaDTO {
    private String nome;
    private Double quantidadeMinima;

    public AtualizarPecaDTO(String nome, Double quantidadeMinima) {
        this.nome = nome;
        this.quantidadeMinima = quantidadeMinima;
    }

    public String getNome() {
        return nome;
    }

    public Double getQuantidadeMinima() {
        return quantidadeMinima;
    }
}
