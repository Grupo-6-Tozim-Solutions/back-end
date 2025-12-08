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
import com.example.Tozin_Solutions_back_end.V2.infrastructure.messaging.RabbitMQNotificacaoProducer;
import jakarta.persistence.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class    CadastrarUsuarioService implements CadastrarUsuarioUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CadastrarUsuarioService.class);

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final PasswordHasherPort passwordHasherPort;
    private final RabbitMQNotificacaoProducer notificacaoProducer;

    public CadastrarUsuarioService(UsuarioRepositoryPort usuarioRepositoryPort, PasswordHasherPort passwordHasherPort, RabbitMQNotificacaoProducer notificacaoProducer) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
        this.passwordHasherPort = passwordHasherPort;
        this.notificacaoProducer = notificacaoProducer;
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

        // Envia notificação de boas-vindas ao RabbitMQ — falhas aqui não devem impedir o cadastro
        try {
            notificacaoProducer.enviarEmailBoasVindas(salvo.getEmail().value(), salvo.getNome().value());
        } catch (Exception e) {
            // Loga o erro e segue; evita que a ausência do RabbitMQ quebre o fluxo de cadastro
            logger.warn("Falha ao enviar mensagem ao RabbitMQ (notificacao de boas-vindas): {}", e.getMessage());
        }

        return new UsuarioOutput(salvo.getNome().value(), salvo.getEmail().value());
    }
}
