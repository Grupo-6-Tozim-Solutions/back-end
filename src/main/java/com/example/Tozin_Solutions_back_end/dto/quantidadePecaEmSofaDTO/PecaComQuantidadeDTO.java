package com.example.Tozin_Solutions_back_end.dto.quantidadePecaEmSofaDTO;

public class PecaComQuantidadeDTO {
    private Long id;
    private String nome;
    private Double quantidade;

    public PecaComQuantidadeDTO(Long id, String nome, Double quantidade) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getQuantidade() {
        return quantidade;
    }
}
