package com.example.Tozin_Solutions_back_end.V2.core.domain.QuantidadePecaEmSofaDomain.excepetion;


import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.excepetion.DomainException;

public class ConfiguracaoNaoEncontradaException extends DomainException {
    public ConfiguracaoNaoEncontradaException(Long sofaId, Long pecaId) {
        super("Configuração não encontrada para sofá ID: " + sofaId + " e peça ID: " + pecaId);
    }

    public ConfiguracaoNaoEncontradaException(Long id) {
        super("Configuração não encontrada com ID: " + id);
    }
}