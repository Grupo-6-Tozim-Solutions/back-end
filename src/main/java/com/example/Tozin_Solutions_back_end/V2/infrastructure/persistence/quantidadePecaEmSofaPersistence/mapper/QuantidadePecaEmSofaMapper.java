package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.quantidadePecaEmSofaPersistence.mapper;

import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.Quantidade;
import com.example.Tozin_Solutions_back_end.V2.core.domain.QuantidadePecaEmSofaDomain.QuantidadePecaEmSofa;
import com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.quantidadePecaEmSofaPersistence.jpa.QuantidadePecaEmSofaJpaEntity;

public class QuantidadePecaEmSofaMapper {

    public static QuantidadePecaEmSofaJpaEntity toEntity(QuantidadePecaEmSofa config) {
        QuantidadePecaEmSofaJpaEntity entity = new QuantidadePecaEmSofaJpaEntity();

        if (config.getId() != null) {
            entity.setId(config.getId());
        }

        entity.setSofaId(config.getSofaId());
        entity.setPecaId(config.getPecaId());
        entity.setQuantidadePeca(config.getQuantidade().value());

        return entity;
    }

    public static QuantidadePecaEmSofa toDomain(QuantidadePecaEmSofaJpaEntity entity) {
        return QuantidadePecaEmSofa.existente(
                entity.getId(),
                entity.getSofaId(),
                entity.getPecaId(),
                new Quantidade(entity.getQuantidadePeca())
        );
    }
}