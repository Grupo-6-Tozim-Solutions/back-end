package com.example.Tozin_Solutions_back_end.V2.infrastructure.web.sofaWeb;

import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.dto.ConfiguracaoPecaSofaOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.dto.ConfigurarPecaNoSofaInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.useCase.ConfigurarPecaNoSofaUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.useCase.ListarConfiguracoesPorSofaUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.useCase.RemoverConfiguracaoUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.application.quantidadePecaEmSofaApplication.useCase.RemoverTodasConfiguracoesUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto.AdicionarPecaInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto.AtualizarSofaInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto.CadastrarSofaInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto.SofaOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.useCase.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v2/sofas")
public class SofaControllerV2 {

    private final CadastrarSofaUseCase cadastrarSofaUseCase;
    private final ListarSofasUseCase listarSofasUseCase;
    private final BuscarSofaPorIdUseCase buscarSofaPorIdUseCase;
    private final AtualizarSofaUseCase atualizarSofaUseCase;
    private final DeletarSofaUseCase deletarSofaUseCase;
    private final AdicionarPecaUseCase adicionarPecaUseCase;
    private final RemoverPecaUseCase removerPecaUseCase;
    private final ProduzirSofaUseCase produzirSofaUseCase;
    private final ConfigurarPecaNoSofaUseCase configurarPecaNoSofaUseCase;
    private final ListarConfiguracoesPorSofaUseCase listarConfiguracoesPorSofaUseCase;
    private final RemoverConfiguracaoUseCase removerConfiguracaoUseCase;
    private final RemoverTodasConfiguracoesUseCase removerTodasConfiguracoesUseCase;

    public SofaControllerV2(CadastrarSofaUseCase cadastrarSofaUseCase,
                            ListarSofasUseCase listarSofasUseCase,
                            BuscarSofaPorIdUseCase buscarSofaPorIdUseCase,
                            AtualizarSofaUseCase atualizarSofaUseCase,
                            DeletarSofaUseCase deletarSofaUseCase,
                            AdicionarPecaUseCase adicionarPecaUseCase,
                            RemoverPecaUseCase removerPecaUseCase,
                            ProduzirSofaUseCase produzirSofaUseCase,
                            ConfigurarPecaNoSofaUseCase configurarPecaNoSofaUseCase,
                            ListarConfiguracoesPorSofaUseCase listarConfiguracoesPorSofaUseCase,
                            RemoverConfiguracaoUseCase removerConfiguracaoUseCase,
                            RemoverTodasConfiguracoesUseCase removerTodasConfiguracoesUseCase) {
        this.cadastrarSofaUseCase = cadastrarSofaUseCase;
        this.listarSofasUseCase = listarSofasUseCase;
        this.buscarSofaPorIdUseCase = buscarSofaPorIdUseCase;
        this.atualizarSofaUseCase = atualizarSofaUseCase;
        this.deletarSofaUseCase = deletarSofaUseCase;
        this.adicionarPecaUseCase = adicionarPecaUseCase;
        this.removerPecaUseCase = removerPecaUseCase;
        this.produzirSofaUseCase = produzirSofaUseCase;
        this.configurarPecaNoSofaUseCase = configurarPecaNoSofaUseCase;
        this.listarConfiguracoesPorSofaUseCase = listarConfiguracoesPorSofaUseCase;
        this.removerConfiguracaoUseCase = removerConfiguracaoUseCase;
        this.removerTodasConfiguracoesUseCase = removerTodasConfiguracoesUseCase;
    }


    @PostMapping
    public ResponseEntity<SofaOutput> cadastrar(@RequestBody @Valid CadastrarSofaInput input) {
        SofaOutput output = cadastrarSofaUseCase.executar(input);
        return ResponseEntity.ok(output);
    }

    @GetMapping
    public ResponseEntity<List<SofaOutput>> listarTodos() {
        List<SofaOutput> sofas = listarSofasUseCase.executar();
        return ResponseEntity.ok(sofas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SofaOutput> buscarPorId(@PathVariable Long id) {
        SofaOutput sofa = buscarSofaPorIdUseCase.executar(id);
        return ResponseEntity.ok(sofa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SofaOutput> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid AtualizarSofaInput input) {
        SofaOutput output = atualizarSofaUseCase.executar(id, input);
        return ResponseEntity.ok(output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarSofaUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/configurar-peca")
    public ResponseEntity<ConfiguracaoPecaSofaOutput> configurarPeca(
            @RequestBody @Valid ConfigurarPecaNoSofaInput input) {
        ConfiguracaoPecaSofaOutput output = configurarPecaNoSofaUseCase.executar(input);
        return ResponseEntity.ok(output);
    }

    @GetMapping("/{sofaId}/configuracoes")
    public ResponseEntity<List<ConfiguracaoPecaSofaOutput>> listarConfiguracoes(
            @PathVariable Long sofaId) {
        List<ConfiguracaoPecaSofaOutput> configuracoes = listarConfiguracoesPorSofaUseCase.executar(sofaId);
        return ResponseEntity.ok(configuracoes);
    }

    @DeleteMapping("/configuracoes/{id}")
    public ResponseEntity<Void> removerConfiguracao(@PathVariable Long id) {
        removerConfiguracaoUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{sofaId}/configuracoes")
    public ResponseEntity<Void> removerTodasConfiguracoes(@PathVariable Long sofaId) {
        removerTodasConfiguracoesUseCase.executar(sofaId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{sofaId}/adicionar-pecas")
    public ResponseEntity<SofaOutput> adicionarPeca(
            @PathVariable Long sofaId,
            @RequestBody @Valid AdicionarPecaInput input) {
        SofaOutput output = adicionarPecaUseCase.executar(sofaId, input);
        return ResponseEntity.ok(output);
    }

    @DeleteMapping("/{sofaId}/pecas/{pecaId}")
    public ResponseEntity<SofaOutput> removerPeca(
            @PathVariable Long sofaId,
            @PathVariable Long pecaId) {
        SofaOutput output = removerPecaUseCase.executar(sofaId, pecaId);
        return ResponseEntity.ok(output);
    }

    @PostMapping("/{sofaId}/produzir")
    public ResponseEntity<Void> produzirSofa(
            @PathVariable Long sofaId,
            @RequestParam Integer quantidade) {
        produzirSofaUseCase.executar(sofaId, quantidade);
        return ResponseEntity.ok().build();
    }

}

