package com.example.Tozin_Solutions_back_end.dto.pecaDTO;

public class AtualizarPecaDTO {
    private String nome;
    private Integer quantidadeMinima;

    public AtualizarPecaDTO(String nome, Integer quantidadeMinima) {
        this.nome = nome;
        this.quantidadeMinima = quantidadeMinima;
    }

    public String getNome() {
        return nome;
    }

    public Integer getQuantidadeMinima() {
        return quantidadeMinima;
    }
}
