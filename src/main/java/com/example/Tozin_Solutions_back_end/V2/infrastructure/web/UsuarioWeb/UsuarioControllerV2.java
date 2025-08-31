package com.example.Tozin_Solutions_back_end.V2.infrastructure.web.UsuarioWeb;

import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.dto.CadastrarUsuarioInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.dto.UsuarioOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.useCase.CadastrarUsuarioUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/usuarios")
public class UsuarioV2Controller {

    private final CadastrarUsuarioUseCase cadastrarUsuarioUseCase;

    public UsuarioV2Controller(CadastrarUsuarioUseCase cadastrarUsuarioUseCase) {
        this.cadastrarUsuarioUseCase = cadastrarUsuarioUseCase;
    }

    @PostMapping
    public ResponseEntity<UsuarioOutput> cadastrarUsuario(@RequestBody CadastrarUsuarioInput cadastrarUsuarioInput) {
        UsuarioOutput usuarioOutput = cadastrarUsuarioUseCase.executar(cadastrarUsuarioInput);
        return ResponseEntity.ok(usuarioOutput);
    }

}
