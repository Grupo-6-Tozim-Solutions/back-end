package com.example.Tozin_Solutions_back_end.controller;

import com.example.Tozin_Solutions_back_end.dto.sofaDTO.AtualizarSofaDTO;
import com.example.Tozin_Solutions_back_end.dto.sofaDTO.CadastrarSofaDTO;
import com.example.Tozin_Solutions_back_end.model.Sofa;
import com.example.Tozin_Solutions_back_end.service.SofaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sofa")
public class SofaController {

    @Autowired
    private SofaService service;

    @PostMapping
    public ResponseEntity cadastrarSofa(@RequestBody CadastrarSofaDTO dadosCadastroSofa){
        return ResponseEntity.ok(service.salvarSofa(dadosCadastroSofa));
    }

    @GetMapping
    public ResponseEntity listarSofas(){
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sofa> retornarSofaPorId(@PathVariable Long id){
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/listarPecas/{id}")
    public ResponseEntity retornarPecasPorSofa(@PathVariable Long id){
        return ResponseEntity.ok(service.listarPecaPorSofa(id));
    }

    @PutMapping
    public ResponseEntity<Sofa> atualizarSofa(@PathVariable Long id, @RequestBody AtualizarSofaDTO dadosSofa){
        return service.atualizarDadosSofa(id, dadosSofa)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/adicionarPeca/{idSofa}/{idPeca}/{quantidade}")
    public ResponseEntity<Sofa> adicionarPeca(@PathVariable Long idSofa, @PathVariable Long idPeca, @PathVariable Integer quantidade){
        return service.adicionarPeca(idSofa, idPeca, quantidade)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/removerPeca/{idSofa}/{idPeca}")
    public ResponseEntity<Sofa> removerPeca(@PathVariable Long idSofa, @PathVariable Long idPeca){
        return service.removerPeca(idSofa, idPeca)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
