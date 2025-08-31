package com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects;

import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.excepetion.DomainException;

public record Quantidade(Double value) {

    public Quantidade {
        if (value == null) {
            throw new DomainException("A quantidade não pode ser nula");
        }
        if (value < 0) {
            throw new DomainException("A quantidade não pode ser negativa");
        }
    }

    public boolean isCritica(Quantidade quantidadeMinima) {
        return value <= quantidadeMinima.value();
    }

    public Quantidade adicionar(Quantidade outra) {
        return new Quantidade(this.value + outra.value);
    }

    public Quantidade subtrair(Quantidade outra) {
        if (outra.value > this.value) {
            throw new DomainException("Quantidade insuficiente em estoque");
        }
        return new Quantidade(this.value - outra.value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

