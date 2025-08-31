package com.example.Tozin_Solutions_back_end.V2.infrastructure.web.pecaWeb;

import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.dto.AtualizarPecaInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.dto.CadastrarPecaInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.dto.PecaOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.useCase.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/pecas")
public class PecaControllerV2 {

    private final CadastrarPecaUseCase cadastrarPecaUseCase;
    private final ListarPecasUseCase listarPecasUseCase;
    private final BuscarPecaPorIdUseCase buscarPecaPorIdUseCase;
    private final AtualizarPecaUseCase atualizarPecaUseCase;
    private final DeletarPecaUseCase deletarPecaUseCase;
    private final VerificarEstoqueCriticoUseCase verificarEstoqueCriticoUseCase;
    private final AdicionarEstoqueUseCase adicionarEstoqueUseCase;
    private final RemoverEstoqueUseCase removerEstoqueUseCase;

    public PecaControllerV2(CadastrarPecaUseCase cadastrarPecaUseCase,
                            ListarPecasUseCase listarPecasUseCase,
                            BuscarPecaPorIdUseCase buscarPecaPorIdUseCase,
                            AtualizarPecaUseCase atualizarPecaUseCase,
                            DeletarPecaUseCase deletarPecaUseCase,
                            VerificarEstoqueCriticoUseCase verificarEstoqueCriticoUseCase,
                            AdicionarEstoqueUseCase adicionarEstoqueUseCase,
                            RemoverEstoqueUseCase removerEstoqueUseCase) {
        this.cadastrarPecaUseCase = cadastrarPecaUseCase;
        this.listarPecasUseCase = listarPecasUseCase;
        this.buscarPecaPorIdUseCase = buscarPecaPorIdUseCase;
        this.atualizarPecaUseCase = atualizarPecaUseCase;
        this.deletarPecaUseCase = deletarPecaUseCase;
        this.verificarEstoqueCriticoUseCase = verificarEstoqueCriticoUseCase;
        this.adicionarEstoqueUseCase = adicionarEstoqueUseCase;
        this.removerEstoqueUseCase = removerEstoqueUseCase;
    }

    @PostMapping
    public ResponseEntity<PecaOutput> cadastrar(@RequestBody @Valid CadastrarPecaInput input) {
        PecaOutput output = cadastrarPecaUseCase.executar(input);
        return ResponseEntity.ok(output);
    }

    @GetMapping("/listarTodas")
    public ResponseEntity<List<PecaOutput>> listarTodas() {
        List<PecaOutput> pecas = listarPecasUseCase.executar();
        return ResponseEntity.ok(pecas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PecaOutput> buscarPorId(@PathVariable Long id) {
        PecaOutput peca = buscarPecaPorIdUseCase.executar(id);
        return ResponseEntity.ok(peca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PecaOutput> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid AtualizarPecaInput input) {
        PecaOutput output = atualizarPecaUseCase.executar(id, input);
        return ResponseEntity.ok(output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarPecaUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estoque-critico")
    public ResponseEntity<List<PecaOutput>> verificarEstoqueCritico() {
        List<PecaOutput> pecasCriticas = verificarEstoqueCriticoUseCase.executar();
        return ResponseEntity.ok(pecasCriticas);
    }

    @PutMapping("/adicionar-estoque/{id}/{quantidadeAdicional}")
    public ResponseEntity<PecaOutput> adicionarEstoque(
            @PathVariable Long id,
            @PathVariable Double quantidadeAdicional) {
        PecaOutput output = adicionarEstoqueUseCase.executar(id, quantidadeAdicional);
        return ResponseEntity.ok(output);
    }

    @PutMapping("remover-estoque/{id}/{quantidadeRemovida}")
    public ResponseEntity<PecaOutput> removerEstoque(
            @PathVariable Long id,
            @PathVariable Double quantidadeRemovida) {
        PecaOutput output = removerEstoqueUseCase.executar(id, quantidadeRemovida);
        return ResponseEntity.ok(output);
    }
}
