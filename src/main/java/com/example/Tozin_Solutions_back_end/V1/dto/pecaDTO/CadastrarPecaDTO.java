package com.example.Tozin_Solutions_back_end.dto.pecaDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CadastrarPecaDTO {
    @NotBlank(message = "O nome deve ser informado!")
    private String nome;

    @NotNull(message = "A quantidade em estoque deve ser informada!")
    private Double quantidadeEstoque;

    @NotNull(message = "A quantidade minima deve ser informada!")
    @Min(value = 5, message = "A quantidade minima não pode ser menor que 5!")
    private Double quantidadeMinima;

    @NotNull(message = "Deve conter o tipo de peça")
    private String tipo;

    public CadastrarPecaDTO(String nome, Double quantidadeEstoque, Double quantidadeMinima) {
        this.nome = nome;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeMinima = quantidadeMinima;
    }

    public String getNome() {
        return nome;
    }

    public Double getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public Double getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public String getTipo() {
        return tipo;
    }
}
