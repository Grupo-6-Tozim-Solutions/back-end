package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.movimentacaoEstoquePersistence.mapper;

import com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain.MovimentacaoEstoque;
import com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain.valueObjects.QuantidadeEntrada;
import com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain.valueObjects.QuantidadeSaida;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.Peca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.NomePeca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.TipoPeca;
import com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.PecaPersistence.Jpa.PecaJpaEntity;
import com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.PecaPersistence.mapper.PecaMapper;
import com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.movimentacaoEstoquePersistence.jpa.MovimentacaoEstoqueJpaEntity;

public class MovimentacaoEstoqueMapper {

    public static MovimentacaoEstoqueJpaEntity toEntity(MovimentacaoEstoque domain) {
        PecaJpaEntity pecaEntity = PecaMapper.DomainToEntity(domain.getPeca());
        return new MovimentacaoEstoqueJpaEntity(
                domain.getId(),
                domain.getDataMovimentacao(),
                pecaEntity,
                domain.getQuantidadeEntrada().value(),
                domain.getQuantidadeSaida().value()
        );
    }
    public static MovimentacaoEstoque toDomain(MovimentacaoEstoqueJpaEntity entity) {
        Peca peca = PecaMapper.toDomain(entity.getPeca());
        QuantidadeEntrada quantidadeEntrada = new QuantidadeEntrada(entity.getQuantidadeEntrada());
        QuantidadeSaida quantidadeSaida = new QuantidadeSaida(entity.getQuantidadeSaida());
        MovimentacaoEstoque movimentacao;
        if (entity.getQuantidadeEntrada() > 0) {
            movimentacao = MovimentacaoEstoque.registrarEntrada(peca, quantidadeEntrada);
        } else if (entity.getQuantidadeSaida() > 0) {
            movimentacao = MovimentacaoEstoque.registrarSaida(peca, quantidadeSaida);
        } else {
            movimentacao = MovimentacaoEstoque.registrarEntrada(peca, new QuantidadeEntrada(0.0));
        }
        movimentacao.setId(entity.getId());
        movimentacao.setDataMovimentacao(entity.getDataMovimentacao());
        return movimentacao;
    }
}