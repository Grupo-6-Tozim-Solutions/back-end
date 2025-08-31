package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.quantidadePecaEmSofaPersistence.jpa;

import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.port.QuantidadePecaEmSofaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.domain.QuantidadePecaEmSofaDomain.QuantidadePecaEmSofa;
import com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.quantidadePecaEmSofaPersistence.mapper.QuantidadePecaEmSofaMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class QuantidadePecaEmSofaRepositoryAdapter implements QuantidadePecaEmSofaRepositoryPort {

    private final QuantidadePecaEmSofaJpaRepository configuracaoJpaRepository;

    public QuantidadePecaEmSofaRepositoryAdapter(QuantidadePecaEmSofaJpaRepository configuracaoJpaRepository) {
        this.configuracaoJpaRepository = configuracaoJpaRepository;
    }

    @Override
    public QuantidadePecaEmSofa salvar(QuantidadePecaEmSofa configuracao) {
        var entity = QuantidadePecaEmSofaMapper.toEntity(configuracao);
        var savedEntity = configuracaoJpaRepository.save(entity);
        return QuantidadePecaEmSofaMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<QuantidadePecaEmSofa> buscarPorId(Long id) {
        return configuracaoJpaRepository.findById(id)
                .map(QuantidadePecaEmSofaMapper::toDomain);
    }

    @Override
    public Optional<QuantidadePecaEmSofa> buscarPorSofaIdEPecaId(Long sofaId, Long pecaId) {
        return configuracaoJpaRepository.findBySofaIdAndPecaId(sofaId, pecaId)
                .map(QuantidadePecaEmSofaMapper::toDomain);
    }

    @Override
    public List<QuantidadePecaEmSofa> buscarPorSofaId(Long sofaId) {
        return configuracaoJpaRepository.findBySofaId(sofaId).stream()
                .map(QuantidadePecaEmSofaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuantidadePecaEmSofa> buscarPorPecaId(Long pecaId) {
        return configuracaoJpaRepository.findByPecaId(pecaId).stream()
                .map(QuantidadePecaEmSofaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deletarPorId(Long id) {
        configuracaoJpaRepository.deleteById(id);
    }

    @Override
    public void deletarPorSofaId(Long sofaId) {
        configuracaoJpaRepository.deleteAllBySofaId(sofaId);
    }

    @Override
    public void deletarPorSofaIdEPecaId(Long sofaId, Long pecaId) {
        configuracaoJpaRepository.deleteBySofaIdAndPecaId(sofaId, pecaId);
    }
}
