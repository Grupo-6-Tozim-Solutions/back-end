package com.example.Tozin_Solutions_back_end.V2.core.domain.producaoSofaDomain.valueObjects;

public class QuantidadeProducao {
    private final Integer value;

    public QuantidadeProducao(Integer value) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("Quantidade de produção deve ser maior que zero");
        }
        this.value = value;
    }

    public Integer value() { return value; }
}
