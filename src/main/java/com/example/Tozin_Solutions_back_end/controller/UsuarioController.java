package com.example.Tozin_Solutions_back_end.controller;

import com.example.Tozin_Solutions_back_end.dto.usuarioDTO.*;
import com.example.Tozin_Solutions_back_end.model.Usuario;
import com.example.Tozin_Solutions_back_end.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    //@PostMapping
    //public ResponseEntity cadastro(@RequestBody @Valid CadastrarUsuarioDTO dadosCadastro){
    //  return ResponseEntity.ok(service.cadastrarUsuario(dadosCadastro));
    //}

    @GetMapping("/{id}")
    public ResponseEntity<DadosUsuarioDTO> retornarPorId(@PathVariable Long id){
        return service.retornarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<DadosUsuarioDTO>> retornarTodos(){
        List<DadosUsuarioDTO> usuariosEncontrados = this.service.retornarTodos();

        if (usuariosEncontrados.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuariosEncontrados);
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

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid CadastrarUsuarioDTO cadastrarUsuarioDTO){

        final Usuario novoUsuario = UsuarioMapper.of(cadastrarUsuarioDTO);
        this.service.cadastrarUsuario(cadastrarUsuarioDTO);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDTO> login(@RequestBody LoginUsuarioDTO loginUsuarioDTO){
        final Usuario usuario = UsuarioMapper.of(loginUsuarioDTO);
        UsuarioTokenDTO usuarioTokenDTO = this.service.autenticar(usuario);

        return ResponseEntity.status(200).body(usuarioTokenDTO);
    }
}
