package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.producaoSofaPersistence.jpa;

import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.dto.MovimentacaoEstoqueInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.dto.MovimentacaoEstoqueOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.producaoSofaApplication.port.ProducaoSofaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain.MovimentacaoEstoque;
import com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain.valueObjects.QuantidadeEntrada;
import com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain.valueObjects.QuantidadeSaida;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.Peca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.excpetion.PecaNaoEncontradaException;
import com.example.Tozin_Solutions_back_end.V2.core.domain.producaoSofaDomain.ProducaoSofa;
import com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.producaoSofaPersistence.mapper.ProducaoSofaMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProducaoSofaRepositoryAdapter implements ProducaoSofaRepositoryPort {

    private final ProducaoSofaJpaRepository producaoSofaJpaRepository;

    public ProducaoSofaRepositoryAdapter(ProducaoSofaJpaRepository producaoSofaJpaRepository) {
        this.producaoSofaJpaRepository = producaoSofaJpaRepository;
    }

    @Override
    public ProducaoSofa salvar(ProducaoSofa producaoSofa) {
        ProducaoSofaJpaEntity entity = ProducaoSofaMapper.toEntity(producaoSofa);
        ProducaoSofaJpaEntity savedEntity = producaoSofaJpaRepository.save(entity);
        return ProducaoSofaMapper.toDomain(savedEntity);
    }

    @Override
    public List<ProducaoSofa> buscarPorSofaId(Long sofaId) {
        return producaoSofaJpaRepository.findBySofaId(sofaId)
                .stream()
                .map(ProducaoSofaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProducaoSofa> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return producaoSofaJpaRepository.findByDataProducaoBetween(inicio, fim)
                .stream()
                .map(ProducaoSofaMapper::toDomain)
                .collect(Collectors.toList());
    }
}
