package com.example.Tozin_Solutions_back_end.V2.infrastructure.web.usuarioWeb;

import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.dto.LoginRequest;
import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.dto.LoginResponse;
import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.useCase.LoginUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/auth")
public class AuthController {

    LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = loginUseCase.executar(loginRequest);
        return ResponseEntity.ok(response);
    }
}
