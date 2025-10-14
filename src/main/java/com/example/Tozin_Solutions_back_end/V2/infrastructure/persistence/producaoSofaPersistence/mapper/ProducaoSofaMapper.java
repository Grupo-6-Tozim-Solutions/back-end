package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.producaoSofaPersistence.mapper;

import com.example.Tozin_Solutions_back_end.V2.core.domain.producaoSofaDomain.ProducaoSofa;
import com.example.Tozin_Solutions_back_end.V2.core.domain.producaoSofaDomain.valueObjects.DataCriacao;
import com.example.Tozin_Solutions_back_end.V2.core.domain.producaoSofaDomain.valueObjects.QuantidadeProducao;
import com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.producaoSofaPersistence.jpa.ProducaoSofaJpaEntity;

public class ProducaoSofaMapper {

    public static ProducaoSofaJpaEntity toEntity(ProducaoSofa domain) {
        return new ProducaoSofaJpaEntity(
                domain.getId(),
                domain.getSofaId(),
                domain.getQuantidade().value(),
                domain.getDataProducao().value()
        );
    }

    public static ProducaoSofa toDomain(ProducaoSofaJpaEntity entity) {
        return new ProducaoSofa(
                entity.getId(),
                entity.getSofaId(),
                new QuantidadeProducao(entity.getQuantidade()),
                new DataCriacao(entity.getDataProducao())
        );
    }
}
