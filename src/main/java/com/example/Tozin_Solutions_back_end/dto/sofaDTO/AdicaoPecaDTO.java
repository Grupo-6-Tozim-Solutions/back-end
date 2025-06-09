package com.example.Tozin_Solutions_back_end.dto.sofaDTO;

public class AdicaoPecaDTO {
    private Long idPeca;
    private Double quantidade;

    public AdicaoPecaDTO(Long idPeca, Double quantidade) {
        this.idPeca = idPeca;
        this.quantidade = quantidade;
    }

    public Long getIdPeca() {
        return idPeca;
    }

    public Double getQuantidade() {
        return quantidade;
    }
}
