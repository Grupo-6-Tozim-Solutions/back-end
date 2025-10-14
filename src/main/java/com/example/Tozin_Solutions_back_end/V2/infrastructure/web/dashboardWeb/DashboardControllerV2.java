package com.example.Tozin_Solutions_back_end.V2.infrastructure.web.dashboardWeb;

import com.example.Tozin_Solutions_back_end.V2.core.application.dashboardApplication.useCase.DashboardUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v2/dashboard")
@Tag(name = "Dashboard V2", description = "Endpoints para dados da dashboard")
public class DashboardControllerV2 {

    private final DashboardUseCase dashboardUseCase;

    public DashboardControllerV2(DashboardUseCase dashboardUseCase) {
        this.dashboardUseCase = dashboardUseCase;
    }

    @GetMapping("/pecas-mais-saida-mes")
    @Operation(summary = "Lista as peças com mais saída no mês atual")
    public ResponseEntity<List<Map<String, Object>>> getPecasMaisSaidaMes() {
        List<Map<String, Object>> pecas = dashboardUseCase.getPecasMaisSaidaMes();
        return ResponseEntity.ok(pecas);
    }

    @GetMapping("/sofas-mais-saida-mes")
    @Operation(summary = "Lista os sofás com mais saída no mês atual")
    public ResponseEntity<List<Map<String, Object>>> getSofasMaisSaidaMes() {
        List<Map<String, Object>> sofas = dashboardUseCase.getSofasMaisSaidaMes();
        return ResponseEntity.ok(sofas);
    }

    @Operation(summary = "Retorna a produção mensal do ano atual")
    @GetMapping
    public ResponseEntity<Map<Integer, Integer>> getProducaoMensalAnoAtual() {
        Map<Integer, Integer> producaoMensal = dashboardUseCase.getProducaoMensalAnoAtual();
        return ResponseEntity.ok(producaoMensal);
    }
}
