package com.example.Tozin_Solutions_back_end.dto.quantidadePecaEmSofaDTO;

import jakarta.validation.constraints.DecimalMin;
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
    @DecimalMin(value = "1.0")
    private Double quantidade;

    public RegistroQuantidadePecaEmSofaDTO(Long idSofa, Long idPeca, Double quantidade) {
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

    public Double getQuantidade() {
        return quantidade;
    }
}
