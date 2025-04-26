package com.example.Tozin_Solutions_back_end.controller;

import com.example.Tozin_Solutions_back_end.dto.usuarioDTO.AtualizarUsuarioDTO;
import com.example.Tozin_Solutions_back_end.dto.usuarioDTO.CadastrarUsuarioDTO;
import com.example.Tozin_Solutions_back_end.dto.usuarioDTO.DadosUsuarioDTO;
import com.example.Tozin_Solutions_back_end.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity cadastro(@RequestBody @Valid CadastrarUsuarioDTO dadosCadastro){
        return ResponseEntity.ok(service.cadastrarUsuario(dadosCadastro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosUsuarioDTO> retornarPorId(@PathVariable Long id){
        return service.retornarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity retornarTodos(){
        return ResponseEntity.ok(service.retornarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosUsuarioDTO> atualizar(@PathVariable Long id, @RequestBody AtualizarUsuarioDTO dados){
        return service.atualizarUsuario(id, dados)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity desativar(@PathVariable Long id){
        service.desativarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
