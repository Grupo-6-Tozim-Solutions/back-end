package com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.dto.CadastrarUsuarioInput;
import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.dto.UsuarioOutput;
import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.port.PasswordHasherPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.port.UsuarioRepositoryPort;
import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.useCase.CadastrarUsuarioUseCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.Usuario;
import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.excepetion.DomainException;
import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.valueObjects.Email;
import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.valueObjects.Nome;
import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.valueObjects.SenhaHash;
import jakarta.persistence.Id;

public class CadastrarUsuarioService implements CadastrarUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final PasswordHasherPort passwordHasherPort;

    public CadastrarUsuarioService(UsuarioRepositoryPort usuarioRepositoryPort, PasswordHasherPort passwordHasherPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
        this.passwordHasherPort = passwordHasherPort;
    }

    public UsuarioOutput executar(CadastrarUsuarioInput entrada) {
        if (usuarioRepositoryPort.existePorEmail(entrada.email())) {
            throw new DomainException("O email inserido já é utilizado");
        }

        if (entrada.senha() == null || entrada.senha().length() < 4 || entrada.senha().length() > 20) {
            throw new DomainException("A senha deve ter entre 4 e 20 caracteres");
        }

        String senhaHash = passwordHasherPort.hash(entrada.senha());

        var usuario = Usuario.criarUsuario(
                new Nome(entrada.nome()),
                new Email(entrada.email()),
                new SenhaHash(senhaHash)
        );
        var salvo = usuarioRepositoryPort.salvar(usuario);
        return new UsuarioOutput(salvo.getNome().value(), salvo.getEmail().value());
    }
}
