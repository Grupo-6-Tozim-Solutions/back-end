package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.PecaPersistence.mapper;

import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.Peca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.NomePeca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.Quantidade;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.TipoPeca;
import com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.PecaPersistence.Jpa.PecaJpaEntity;

public class PecaMapper {

    public static PecaJpaEntity DomainToEntity(Peca peca) {
        PecaJpaEntity entity = new PecaJpaEntity();
        if(peca.getId() != null) {
            entity.setId(peca.getId());
        }

        entity.setNome(peca.getNome().value());
        entity.setQuantidadeEstoque(peca.getQuantidadeEstoque().value());
        entity.setQuantidadeMinima(peca.getQuantidadeMinima().value());
        entity.setDataCadastro(peca.getDataCadastro());
        entity.setTipo(peca.getTipo().name());
        return entity;
    }

    public static Peca toDomain(PecaJpaEntity entity) {
        return Peca.existente(
                entity.getId(),
                new NomePeca(entity.getNome()),
                new Quantidade(entity.getQuantidadeEstoque()),
                new Quantidade(entity.getQuantidadeMinima()),
                TipoPeca.fromString(entity.getTipo()),
                entity.getDataCadastro()
        );
    }
}
