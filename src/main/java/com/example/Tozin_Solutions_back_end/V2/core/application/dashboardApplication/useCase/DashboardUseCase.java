package com.example.Tozin_Solutions_back_end.V2.core.application.dashboardApplication.useCase;

import java.util.List;
import java.util.Map;

public interface DashboardUseCase {
    List<Map<String, Object>> getPecasMaisSaidaMes();
    List<Map<String, Object>> getSofasMaisSaidaMes();
    Map<Integer, Integer> getProducaoMensalAnoAtual();
}
