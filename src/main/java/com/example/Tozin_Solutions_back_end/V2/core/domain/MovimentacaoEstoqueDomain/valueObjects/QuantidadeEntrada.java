package com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain.valueObjects;

public record QuantidadeEntrada(Double value) {

    public QuantidadeEntrada(Double value) {
        if(value < 0){
            throw new IllegalArgumentException("A quantidade de entrada não pode ser negativa");
        }
        this.value = value;
    }

    @Override
    public Double value() {
        return value == null ? 0 : value;
    }
}
