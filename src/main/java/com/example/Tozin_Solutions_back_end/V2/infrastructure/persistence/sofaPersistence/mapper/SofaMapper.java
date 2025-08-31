package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.sofaPersistence.mapper;

import com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain.Sofa;
import com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain.ValueObjects.CaminhoImagem;
import com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain.ValueObjects.ModeloSofa;
import com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.sofaPersistence.jpa.SofaJpaEntity;

public class SofaMapper {

    public static SofaJpaEntity toEntity(Sofa sofa) {
        SofaJpaEntity entity = new SofaJpaEntity();

        if (sofa.getId() != null) {
            entity.setId(sofa.getId());
        }

        entity.setModelo(sofa.getModelo().value());
        entity.setCaminhoImagem(sofa.getCaminhoImagem().value());
        entity.setDataCadastro(sofa.getDataCadastro());

        return entity;
    }

    public static Sofa toDomain(SofaJpaEntity entity) {
        return Sofa.existente(
                entity.getId(),
                new ModeloSofa(entity.getModelo()),
                new CaminhoImagem(entity.getCaminhoImagem()),
                entity.getDataCadastro()
        );
    }
}
