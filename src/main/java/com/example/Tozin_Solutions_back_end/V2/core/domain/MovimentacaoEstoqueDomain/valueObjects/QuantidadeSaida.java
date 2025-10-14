package com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain.valueObjects;

public class QuantidadeSaida {
    private final Double value;

    public QuantidadeSaida(Double value) {
        // ✅ SOLUÇÃO SIMPLES: Apenas garantir que não é null
        if (value == null) {
            throw new IllegalArgumentException("A quantidade de saida não pode ser nula");
        }

        // ✅ FORÇAR valor positivo (resolvendo problema de -0.0)
        this.value = Math.abs(value);

        // Log para debug
        System.out.println("QuantidadeSaida criada - valor original: " + value + ", valor final: " + this.value);
    }

    public Double value() {
        return value;
    }
}
