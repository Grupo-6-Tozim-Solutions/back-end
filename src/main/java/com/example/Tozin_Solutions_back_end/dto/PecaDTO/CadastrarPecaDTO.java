package com.example.Tozin_Solutions_back_end.dto.PecaDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CadastrarPecaDTO {
    @NotBlank(message = "O nome deve ser informado!")
    private String nome;

    @NotNull(message = "A quantidade em estoque deve ser informada!")
    private Integer quantidadeEstoque;

    @NotNull(message = "A quantidade minima deve ser informada!")
    @Min(value = 5, message = "A quantidade minima não pode ser menor que 5!")
    private Integer quantidadeMinima;

    public CadastrarPecaDTO(String nome, Integer quantidadeEstoque, Integer quantidadeMinima, Integer quantidadeAlerta) {
        this.nome = nome;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeMinima = quantidadeMinima;
    }

    public String getNome() {
        return nome;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public Integer getQuantidadeMinima() {
        return quantidadeMinima;
    }
}
