package com.example.Tozin_Solutions_back_end.dto.sofaDTO;

public class AdicaoPecaDTO {
    private Long idPeca;
    private Integer quantidade;

    public AdicaoPecaDTO(Long idPeca, Integer quantidade) {
        this.idPeca = idPeca;
        this.quantidade = quantidade;
    }

    public Long getIdPeca() {
        return idPeca;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
