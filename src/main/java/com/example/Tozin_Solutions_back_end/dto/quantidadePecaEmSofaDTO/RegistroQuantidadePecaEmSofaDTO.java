package com.example.Tozin_Solutions_back_end.dto.quantidadePecaEmSofaDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RegistroQuantidadePecaEmSofaDTO {
    @NotNull
    @Min(value = 1)
    private Long idSofa;

    @NotNull
    @Min(value = 1)
    private Long idPeca;

    @NotNull
    @Min(value = 1)
    private Integer quantidade;

    public RegistroQuantidadePecaEmSofaDTO(Long idSofa, Long idPeca, Integer quantidade) {
        this.idSofa = idSofa;
        this.idPeca = idPeca;
        this.quantidade = quantidade;
    }

    public Long getIdSofa() {
        return idSofa;
    }

    public Long getIdPeca() {
        return idPeca;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
