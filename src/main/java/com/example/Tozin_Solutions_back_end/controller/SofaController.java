package com.example.Tozin_Solutions_back_end.controller;

import com.example.Tozin_Solutions_back_end.dto.sofaDTO.AdicaoPecaDTO;
import com.example.Tozin_Solutions_back_end.dto.sofaDTO.AtualizarSofaDTO;
import com.example.Tozin_Solutions_back_end.dto.sofaDTO.CadastrarSofaDTO;
import com.example.Tozin_Solutions_back_end.model.Sofa;
import com.example.Tozin_Solutions_back_end.service.SofaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/sofa")
public class SofaController {

    @Autowired
    private SofaService service;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Sofa> cadastrarSofaComImagem(
            @RequestPart("sofa") String dadosSofaJson,
            @RequestPart("imagem") MultipartFile imagem) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        CadastrarSofaDTO sofa = objectMapper.readValue(dadosSofaJson, CadastrarSofaDTO.class);

        if (imagem.isEmpty()) {
            return ResponseEntity.badRequest().build(); // ou lançar uma exceção personalizada
        }

        Sofa sofaSalvo = service.salvarSofaComImagem(sofa, imagem);
        return ResponseEntity.ok(sofaSalvo);
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

    @PutMapping("/adicionarPeca/{idSofa}")
    public ResponseEntity<Sofa> adicionarPeca(@PathVariable Long idSofa, @RequestBody List<AdicaoPecaDTO> pecasAdicionadas){
        return service.adicionarPeca(idSofa, pecasAdicionadas)
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
