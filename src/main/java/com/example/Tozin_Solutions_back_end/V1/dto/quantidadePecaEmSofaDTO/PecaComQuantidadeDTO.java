package com.example.Tozin_Solutions_back_end.V1.dto.quantidadePecaEmSofaDTO;

public class PecaComQuantidadeDTO {
    private Long id;
    private String nome;
    private Double quantidade;
    private String tipo;

    public PecaComQuantidadeDTO(Long id, String nome, Double quantidade, String tipo) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.tipo = tipo;
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

    public String getTipo() {
        return tipo;
    }
}
