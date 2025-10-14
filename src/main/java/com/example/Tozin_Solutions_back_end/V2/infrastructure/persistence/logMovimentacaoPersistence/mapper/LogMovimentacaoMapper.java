package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.logMovimentacaoPersistence.mapper;

import com.example.Tozin_Solutions_back_end.V2.core.domain.logMovimentacaoDomain.LogMovimentacao;
import com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.logMovimentacaoPersistence.jpa.LogMovimentacaoJpaEntity;

public class LogMovimentacaoMapper {

    public static LogMovimentacao toDomain(LogMovimentacaoJpaEntity entity) {
        return new LogMovimentacao(
                entity.getId(),
                entity.getMovimentacaoId(),
                entity.getDataMovimentacao(),
                entity.getTipoPeca(),
                entity.getNomePeca(),
                entity.getQuantidadeEntrada(),
                entity.getQuantidadeSaida(),
                entity.getDataRegistro()
        );
    }

    public static LogMovimentacaoJpaEntity toEntity(LogMovimentacao domain) {
        return new LogMovimentacaoJpaEntity(
                domain.getId(),
                domain.getMovimentacaoId(),
                domain.getDataMovimentacao(),
                domain.getTipoPeca(),
                domain.getNomePeca(),
                domain.getQuantidadeEntrada(),
                domain.getQuantidadeSaida(),
                domain.getDataRegistro()
        );
    }
}
