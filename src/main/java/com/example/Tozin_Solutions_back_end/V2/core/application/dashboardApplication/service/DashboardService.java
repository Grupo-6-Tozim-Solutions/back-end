package com.example.Tozin_Solutions_back_end.V2.core.application.dashboardApplication.service;


import com.example.Tozin_Solutions_back_end.V2.core.application.dashboardApplication.useCase.DashboardUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.projectionDomain.ProducaoMensal;
import com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.logMovimentacaoPersistence.jpa.LogMovimentacaoJpaRepository;
import com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.producaoSofaPersistence.jpa.ProducaoSofaJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class DashboardService implements DashboardUseCase {

    private final LogMovimentacaoJpaRepository logMovimentacaoRepository;
    private final ProducaoSofaJpaRepository producaoSofaRepository;

    public DashboardService(LogMovimentacaoJpaRepository logMovimentacaoRepository,
                            ProducaoSofaJpaRepository producaoSofaRepository) {
        this.logMovimentacaoRepository = logMovimentacaoRepository;
        this.producaoSofaRepository = producaoSofaRepository;
    }

    @Override
    public List<Map<String, Object>> getPecasMaisSaidaMes() {
        return logMovimentacaoRepository.findPecasMaisSaidaMes();
    }

    @Override
    public List<Map<String, Object>> getSofasMaisSaidaMes() {
        return logMovimentacaoRepository.findSofasMaisSaidaMes();
    }

    @Override
    public Map<Integer, Integer> getProducaoMensalAnoAtual() {
        List<ProducaoMensal> producaoPorMes = producaoSofaRepository.findProducaoAgrupadaPorMesAnoAtual();

        Map<Integer, Integer> resultado = IntStream.rangeClosed(1, 12)
                .boxed()
                .collect(Collectors.toMap(
                        mes -> mes,
                        mes -> 0
                ));

        producaoPorMes.forEach(item ->
                resultado.put(item.getMes(), item.getTotal())
        );

        return resultado;
    }
}
