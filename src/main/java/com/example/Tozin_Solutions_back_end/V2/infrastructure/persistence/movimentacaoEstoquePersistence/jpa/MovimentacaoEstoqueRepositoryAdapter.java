package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.movimentacaoEstoquePersistence.jpa;

import com.example.Tozin_Solutions_back_end.V1.repository.MovimentacaoEstoqueRepository;
import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.dto.MovimentacaoEstoqueInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.dto.MovimentacaoEstoqueOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.movimentacaoEstoqueApplication.port.MovimentacaoEstoquePort;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.port.PecaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain.MovimentacaoEstoque;
import com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain.valueObjects.QuantidadeEntrada;
import com.example.Tozin_Solutions_back_end.V2.core.domain.MovimentacaoEstoqueDomain.valueObjects.QuantidadeSaida;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.Peca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.excpetion.PecaNaoEncontradaException;
import com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.movimentacaoEstoquePersistence.mapper.MovimentacaoEstoqueMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MovimentacaoEstoqueRepositoryAdapter implements MovimentacaoEstoquePort {

    private final MovimentacaoEstoqueJpaRepository movimentacaoEstoqueJpaRepository;
    private final PecaRepositoryPort pecaRepositoryPort;

    // ✅ Adicione o PecaRepositoryPort no construtor
    public MovimentacaoEstoqueRepositoryAdapter(MovimentacaoEstoqueJpaRepository movimentacaoEstoqueJpaRepository,
                                                PecaRepositoryPort pecaRepositoryPort) {
        this.movimentacaoEstoqueJpaRepository = movimentacaoEstoqueJpaRepository;
        this.pecaRepositoryPort = pecaRepositoryPort;
    }

    @Override
    public MovimentacaoEstoque salvar(MovimentacaoEstoque movimentacaoEstoque) {
        var entity = MovimentacaoEstoqueMapper.toEntity(movimentacaoEstoque);
        var savedEntity = movimentacaoEstoqueJpaRepository.save(entity);
        return MovimentacaoEstoqueMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<MovimentacaoEstoque> buscarPorId(Long id) {
        return movimentacaoEstoqueJpaRepository.findById(id)
                .map(MovimentacaoEstoqueMapper::toDomain);
    }

    @Override
    public List<MovimentacaoEstoque> buscarTodas() {
        return movimentacaoEstoqueJpaRepository.findAll().stream()
                .map(MovimentacaoEstoqueMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deletarPorPecaId(Long pecaId) {
        movimentacaoEstoqueJpaRepository.deleteByPecaId(pecaId);
    }

}
