package com.example.Tozin_Solutions_back_end.V1.controller;

import com.example.Tozin_Solutions_back_end.V1.dto.pecaDTO.AtualizarPecaDTO;
import com.example.Tozin_Solutions_back_end.V1.dto.pecaDTO.CadastrarPecaDTO;
import com.example.Tozin_Solutions_back_end.V1.model.Peca;
import com.example.Tozin_Solutions_back_end.V1.service.PecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/peca")
public class PecaController {

    @Autowired
    private PecaService service;

    @PostMapping
    public ResponseEntity cadastrarPeca(@RequestBody CadastrarPecaDTO dadosCadastroPeca){
        return ResponseEntity.ok(service.salvarPeca(dadosCadastroPeca));
    }

    @GetMapping("/listarTodas")
    public ResponseEntity listarPecas(){
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Peca> buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/adicionarQuantidade/{id}/{quantidadeAdicional}")
    public ResponseEntity<Peca> adicionarQuantidade(@PathVariable Long id, @PathVariable Double quantidadeAdicional){
        return service.adicionarQuantidadeEstoque(id, quantidadeAdicional)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/removerQuantidade/{id}/{quantidadeRemovida}")
    public ResponseEntity<Peca> removerQuantidade(@PathVariable Long id, @PathVariable Double quantidadeRemovida){
        return service.removerQuantidadeEstoque(id, quantidadeRemovida)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pecasComValorCritico")
    public ResponseEntity verificarPecasComValorCritico(){
        return ResponseEntity.ok(service.verificarPecasComValorCritico());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Peca> atualizarPecas(@PathVariable Long id, @RequestBody AtualizarPecaDTO novosDados){
        return service.atualizarPeca(id, novosDados)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarPeca(@PathVariable Long id){
        return service.deletarPeca(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
