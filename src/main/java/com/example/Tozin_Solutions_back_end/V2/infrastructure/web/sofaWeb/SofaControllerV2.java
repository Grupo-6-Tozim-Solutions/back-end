    package com.example.Tozin_Solutions_back_end.V2.infrastructure.web.sofaWeb;

    import com.example.Tozin_Solutions_back_end.V2.core.application.producaoSofaApplication.useCase.ProduzirSofaUseCase;
    import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto.*;
    import com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.useCase.*;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import jakarta.validation.Valid;
    import org.springframework.http.MediaType;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;

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
        private final ListarPecasPorSofaUseCase listarPecasPorSofaUseCase;

        public SofaControllerV2(CadastrarSofaUseCase cadastrarSofaUseCase,
                                ListarSofasUseCase listarSofasUseCase,
                                BuscarSofaPorIdUseCase buscarSofaPorIdUseCase,
                                AtualizarSofaUseCase atualizarSofaUseCase,
                                DeletarSofaUseCase deletarSofaUseCase,
                                AdicionarPecaUseCase adicionarPecaUseCase,
                                RemoverPecaUseCase removerPecaUseCase,
                                ProduzirSofaUseCase produzirSofaUseCase,
                                ListarPecasPorSofaUseCase listarPecasPorSofaUseCase) {
            this.cadastrarSofaUseCase = cadastrarSofaUseCase;
            this.listarSofasUseCase = listarSofasUseCase;
            this.buscarSofaPorIdUseCase = buscarSofaPorIdUseCase;
            this.atualizarSofaUseCase = atualizarSofaUseCase;
            this.deletarSofaUseCase = deletarSofaUseCase;
            this.adicionarPecaUseCase = adicionarPecaUseCase;
            this.removerPecaUseCase = removerPecaUseCase;
            this.produzirSofaUseCase = produzirSofaUseCase;
            this.listarPecasPorSofaUseCase = listarPecasPorSofaUseCase;
        }

        @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<SofaOutput> cadastrar(
                @RequestPart("dados") @Valid CadastrarSofaInput input,
                @RequestPart(value = "imagem", required = false) MultipartFile imagem) {

            SofaOutput output = cadastrarSofaUseCase.executar(input, imagem);
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

        @PutMapping(value = "/atualizar/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<SofaOutput> atualizar(
                @PathVariable Long id,
                @RequestPart("dados") String dadosJson,
                @RequestPart(value = "imagem", required = false) MultipartFile imagem) {
            ObjectMapper objectMapper = new ObjectMapper();
            AtualizarSofaInput input;
            try {
                input = objectMapper.readValue(dadosJson, AtualizarSofaInput.class);
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
            SofaOutput output = atualizarSofaUseCase.executar(id, input, imagem);
            return ResponseEntity.ok(output);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletar(@PathVariable Long id) {
            deletarSofaUseCase.executar(id);
            return ResponseEntity.noContent().build();
        }

        @PutMapping("adicionarPeca/{sofaId}")
        public ResponseEntity<SofaOutput> adicionarPeca(
                @PathVariable Long sofaId,
                @RequestBody @Valid AdicionarPecaInput input) {
            SofaOutput output = adicionarPecaUseCase.executar(sofaId, input);
            return ResponseEntity.ok(output);
        }

        @GetMapping("listarPecas/{sofaId}")
        public ResponseEntity<List<PecaSofaOutput>> listarPecas(@PathVariable Long sofaId) {
            List<PecaSofaOutput> pecas = listarPecasPorSofaUseCase.executar(sofaId);
            return ResponseEntity.ok(pecas);
        }

        @DeleteMapping("/removerPeca/{sofaId}/{pecaId}")
        public ResponseEntity<SofaOutput> removerPeca(
                @PathVariable Long sofaId,
                @PathVariable Long pecaId) {
            SofaOutput output = removerPecaUseCase.executar(sofaId, pecaId);
            return ResponseEntity.ok(output);
        }

        @PostMapping("/produzir/{sofaId}")
        public ResponseEntity<Void> produzirSofa(
                @PathVariable Long sofaId,
                @RequestParam Integer quantidade) {
            produzirSofaUseCase.executar(sofaId, quantidade);
            return ResponseEntity.ok().build();
        }
    }

