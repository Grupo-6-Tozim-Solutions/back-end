package com.example.Tozin_Solutions_back_end.V2.infrastructure.config;

import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.port.PasswordHasherPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.port.UsuarioRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.service.CadastrarUsuarioService;
import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.useCase.CadastrarUsuarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CadastrarUsuarioUseCase cadastrarUsuarioUseCase(
            UsuarioRepositoryPort usuarioRepositoryPort,
            PasswordHasherPort passwordHasherPort) {
        return new CadastrarUsuarioService(usuarioRepositoryPort, passwordHasherPort);
    }


}
