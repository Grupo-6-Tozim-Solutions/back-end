package com.example.Tozin_Solutions_back_end.V1.controller;

import com.example.Tozin_Solutions_back_end.V1.model.MovimentacaoEstoque;
import com.example.Tozin_Solutions_back_end.V1.service.MovimentacaoEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movimentacaoEstoque")
public class MovimentacaoEstoqueController {

    @Autowired
    private MovimentacaoEstoqueService service;

    @GetMapping
    public ResponseEntity retornarTodas(){
        return ResponseEntity.ok(service.retornarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentacaoEstoque> retornarPorId(@PathVariable Long id){
        return service.retornarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
