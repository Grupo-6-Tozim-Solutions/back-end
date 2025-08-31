package com.example.Tozin_Solutions_back_end.V1.service;

import com.example.Tozin_Solutions_back_end.V1.model.LogMovimentacao;
import com.example.Tozin_Solutions_back_end.V1.repository.LogMovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogMovimentacaoService {

    @Autowired
    private LogMovimentacaoRepository logMovimentacaoRepository;

    public List<LogMovimentacao> listarTodas() {
        return logMovimentacaoRepository.findAll();
    }

    public LogMovimentacao buscarPorId(Long id) {
        return logMovimentacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimentação não encontrada"));
    }

    public List<LogMovimentacao> filtrarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return logMovimentacaoRepository.findByDataMovimentacaoBetween(inicio, fim);
    }

    public List<LogMovimentacao> buscarPorNomePeca(String nomePeca) {
        return logMovimentacaoRepository.findByNomePecaContainingIgnoreCase(nomePeca);
    }
}