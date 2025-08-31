package com.example.Tozin_Solutions_back_end.controller;

import com.example.Tozin_Solutions_back_end.service.MovimentacaoEstoqueService;
import com.example.Tozin_Solutions_back_end.service.SofaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private MovimentacaoEstoqueService movimentacaoEstoqueService;

    @Autowired
    private SofaService sofaService;

    @GetMapping("/pecas-mais-saida-mes")
    public List<Map<String, Object>> pecasMaisSaidaMes() {
        return movimentacaoEstoqueService.getPecasMaisSaidaMes();
    }

    @GetMapping("/sofas-mais-saida-mes")
    public List<Map<String, Object>> sofasMaisSaidaMes() {
        return sofaService.getSofasMaisSaidaMes();
    }

    @GetMapping
    public ResponseEntity<Map<Integer, Integer>> getProducaoMensalAnoAtual() {
        return new ResponseEntity<>(sofaService.getProducaoMensalAnoAtual(), HttpStatus.OK);
    }

}
