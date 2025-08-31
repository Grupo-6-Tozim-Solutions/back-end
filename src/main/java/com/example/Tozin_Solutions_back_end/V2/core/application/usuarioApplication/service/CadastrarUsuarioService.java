package com.example.Tozin_Solutions_back_end.V2.application.usuarioApplication.service;

import com.example.Tozin_Solutions_back_end.V2.application.usuarioApplication.dto.CadastrarUsuarioInput;
import com.example.Tozin_Solutions_back_end.V2.application.usuarioApplication.dto.UsuarioOutput;
import com.example.Tozin_Solutions_back_end.V2.application.usuarioApplication.port.PasswordHasher;
import com.example.Tozin_Solutions_back_end.V2.application.usuarioApplication.repository.UsuarioRepository;
import com.example.Tozin_Solutions_back_end.V2.domain.usuarioDomain.Usuario;
import com.example.Tozin_Solutions_back_end.V2.domain.usuarioDomain.excepetion.DomainException;
import com.example.Tozin_Solutions_back_end.V2.domain.usuarioDomain.valueObjects.Email;
import com.example.Tozin_Solutions_back_end.V2.domain.usuarioDomain.valueObjects.Nome;
import com.example.Tozin_Solutions_back_end.V2.domain.usuarioDomain.valueObjects.SenhaHash;

public class CadastrarUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordHasher passwordHasher;

    public CadastrarUsuarioService(UsuarioRepository usuarioRepository, PasswordHasher passwordHasher) {
        this.usuarioRepository = usuarioRepository;
        this.passwordHasher = passwordHasher;
    }

    public UsuarioOutput executar(CadastrarUsuarioInput entrada) {
        if (usuarioRepository.existePorEmail(entrada.Email())) {
            throw new DomainException("O email inserido já é utilizado");
        }

        if (entrada.senha() == null || entrada.senha().length() < 4 || entrada.senha().length() > 20) {
            throw new DomainException("A senha deve ter entre 4 e 20 caracteres");
        }

        String senhaHash = passwordHasher.hash(entrada.senha());

        var usuario = Usuario.criarUsuario(
                new Nome(entrada.nome()),
                new Email(entrada.Email()),
                new SenhaHash(senhaHash)
        );
        var salvo = usuarioRepository.salvar(usuario);
        return new UsuarioOutput(salvo.getNome().value(), salvo.getEmail().value());
    }
}
