package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.logMovimentacaoPersistence.jpa;

import com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.port.LogMovimentacaoPort;
import com.example.Tozin_Solutions_back_end.V2.core.domain.logMovimentacaoDomain.LogMovimentacao;
import com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.logMovimentacaoPersistence.mapper.LogMovimentacaoMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LogMovimentacaoRepositoryAdapter implements LogMovimentacaoPort {

    private final LogMovimentacaoJpaRepository logMovimentacaoJpaRepository;

    public LogMovimentacaoRepositoryAdapter(LogMovimentacaoJpaRepository logMovimentacaoJpaRepository) {
        this.logMovimentacaoJpaRepository = logMovimentacaoJpaRepository;
    }

    @Override
    public List<LogMovimentacao> buscarTodos() {
        return logMovimentacaoJpaRepository.findAll().stream()
                .map(LogMovimentacaoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<LogMovimentacao> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return logMovimentacaoJpaRepository.findByDataMovimentacaoBetween(inicio, fim).stream()
                .map(LogMovimentacaoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<LogMovimentacao> buscarPorNomePeca(String nomePeca) {
        return logMovimentacaoJpaRepository.findByNomePecaContainingIgnoreCase(nomePeca).stream()
                .map(LogMovimentacaoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public LogMovimentacao buscarPorId(Long id) {
        return logMovimentacaoJpaRepository.findById(id)
                .map(LogMovimentacaoMapper::toDomain)
                .orElse(null);
    }
}
