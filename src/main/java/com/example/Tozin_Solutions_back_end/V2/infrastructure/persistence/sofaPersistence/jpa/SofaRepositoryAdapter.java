package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.sofaPersistence.jpa;

import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.port.SofaRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain.Sofa;
import com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.sofaPersistence.mapper.SofaMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SofaRepositoryAdapter implements SofaRepositoryPort {

    private final SofaJpaRepository sofaJpaRepository;

    public SofaRepositoryAdapter(SofaJpaRepository sofaJpaRepository) {
        this.sofaJpaRepository = sofaJpaRepository;
    }

    @Override
    public Sofa salvar(Sofa sofa) {
        var entity = SofaMapper.toEntity(sofa);
        var savedEntity = sofaJpaRepository.save(entity);
        return SofaMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Sofa> buscarPorId(Long id) {
        return sofaJpaRepository.findById(id)
                .map(SofaMapper::toDomain);
    }

    @Override
    public Optional<Sofa> buscarPorModelo(String modelo) {
        return sofaJpaRepository.findByModeloIgnoreCase(modelo)
                .map(SofaMapper::toDomain);
    }

    @Override
    public List<Sofa> buscarTodos() {
        return sofaJpaRepository.findAll().stream()
                .map(SofaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existePorModelo(String modelo) {
        return sofaJpaRepository.existsByModeloIgnoreCase(modelo);
    }

    @Override
    public void deletarPorId(Long id) {
        sofaJpaRepository.deleteById(id);
    }

    @Override
    public Page<Sofa> buscarTodosPaginados(Pageable pageable, String filter) {
        if (filter != null && !filter.trim().isEmpty()) {
            return sofaJpaRepository.findByModeloContainingIgnoreCase(filter.trim(), pageable)
                    .map(SofaMapper::toDomain);
        } else {
            return sofaJpaRepository.findAll(pageable).map(SofaMapper::toDomain);
        }
    }

}
