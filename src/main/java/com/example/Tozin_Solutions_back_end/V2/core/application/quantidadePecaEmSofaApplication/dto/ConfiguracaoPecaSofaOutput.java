package com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.dto;


public record ConfiguracaoPecaSofaOutput(
        Long id,
        Long sofaId,
        Long pecaId,
        Double quantidade
) {}
