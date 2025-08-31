package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.PecaPersistence.Jpa;

import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.port.PecaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.Peca;
import com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.PecaPersistence.mapper.PecaMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PecaRepositoryAdapter implements PecaRepositoryPort {

    private final PecaJpaRepository pecaJpaRepository;

    public PecaRepositoryAdapter(PecaJpaRepository pecaJpaRepository) {
        this.pecaJpaRepository = pecaJpaRepository;
    }

    @Override
    public Peca salvar(Peca peca) {
        var entity = PecaMapper.DomainToEntity(peca);
        var savedEntity = pecaJpaRepository.save(entity);
        return PecaMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Peca> buscarPorId(Long id) {
        return pecaJpaRepository.findById(id)
                .map(PecaMapper::toDomain);
    }

    @Override
    public Optional<Peca> buscarPorNome(String nome) {
        return pecaJpaRepository.findByNomeIgnoreCase(nome)
                .map(PecaMapper::toDomain);
    }

    @Override
    public List<Peca> buscarTodas() {
        return pecaJpaRepository.findAllByOrderByNomeAsc().stream()
                .map(PecaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existePorNome(String nome) {
        return pecaJpaRepository.existsByNomeIgnoreCase(nome);
    }

    @Override
    public void deletarPorId(Long id) {
        pecaJpaRepository.deleteById(id);
    }

    @Override
    public List<Peca> buscarComEstoqueCritico() {
        return pecaJpaRepository.findPecasComEstoqueCritico().stream()
                .map(PecaMapper::toDomain)
                .collect(Collectors.toList());
    }
}
