package com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects;

import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.excepetion.DomainException;

import java.util.Objects;

public record NomePeca(String value) {

    public NomePeca(String value){
        if(Objects.isNull(value) || value.trim().isEmpty()){
            throw new DomainException("O nome da peça não pode estar vazio");
        }
        if (value.length() < 3){
            throw new DomainException("O nome da peça não pode ter menos que 3 caracteres");
        }
        if(value.length() > 25){
            throw new DomainException("O nome da peça não pode ultrapassar 25 caracteres");
        }
        this.value = value.trim();
    }

    @Override
    public String toString() { return value; }
}
