package com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.excpetion;

import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.excepetion.DomainException;

public class EstoqueInsuficienteException extends DomainException {
    public EstoqueInsuficienteException(String nomePeca, Double quantidadeSolicitada, Double quantidadeDisponivel){
        super(String.format("Estoque insuficiente para peça %s. Solicitado: %.2f, Disponível: %.2f",
                nomePeca, quantidadeSolicitada, quantidadeDisponivel));
    }
}