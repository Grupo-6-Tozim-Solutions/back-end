package com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.dto.LoginRequest;
import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.dto.LoginResponse;
import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.port.PasswordHasherPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.port.TokenProviderPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.port.UsuarioRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.useCase.LoginUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.Usuario;
import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.excepetion.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginUseCase {

   private final UsuarioRepositoryPort usuarioRepositoryPort;
   private final TokenProviderPort tokenProviderPort;
   private final PasswordHasherPort passwordHasherPort;

   public LoginService(UsuarioRepositoryPort usuarioRepositoryPort, TokenProviderPort tokenProviderPort, PasswordHasherPort passwordHasherPort) {
       this.usuarioRepositoryPort = usuarioRepositoryPort;
       this.tokenProviderPort = tokenProviderPort;
       this.passwordHasherPort = passwordHasherPort;
   }
   @Override
   public LoginResponse executar(LoginRequest loginRequest) {
       Usuario usuario = usuarioRepositoryPort.buscarPorEmail(loginRequest.email())
               .orElseThrow(() -> new DomainException("Credenciais inválidas"));
       if(!passwordHasherPort.matches(loginRequest.senha(),  usuario.getSenhaHash().value())) {
           throw new DomainException("Credenciais inválidas");
       }
       String token = tokenProviderPort.generateToken(
               usuario.getId(),
               usuario.getEmail().value(),
               usuario.getNome().value()
       );
       return new LoginResponse(
               token,
               usuario.getId(),
               usuario.getEmail().value(),
               usuario.getNome().value()
       );
   }
}
