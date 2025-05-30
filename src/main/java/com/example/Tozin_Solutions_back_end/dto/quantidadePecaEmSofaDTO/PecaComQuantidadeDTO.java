package com.example.Tozin_Solutions_back_end.dto.quantidadePecaEmSofaDTO;

public class PecaComQuantidadeDTO {
    private Long id;
    private String nome;
    private Integer quantidade;

    public PecaComQuantidadeDTO(Long id, String nome, Integer quantidade) {
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

    public Integer getQuantidade() {
        return quantidade;
    }
}
