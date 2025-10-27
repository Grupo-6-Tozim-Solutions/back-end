package com.example.Tozin_Solutions_back_end.V2.infrastructure.web.notificacaoWeb;

import com.example.Tozin_Solutions_back_end.V2.core.application.notificacaoApplication.useCase.EnviarNotificacaoUserCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.NotificacaoDomain.Notificacao;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notificacao")
public class NotificacaoController {
    private final EnviarNotificacaoUserCase enviarNotificacaoUserCase;

    public NotificacaoController(EnviarNotificacaoUserCase enviarNotificacaoUserCase) {
        this.enviarNotificacaoUserCase = enviarNotificacaoUserCase;
    }

    @PostMapping
    public void enviar(@RequestBody Notificacao notificacao) {
        enviarNotificacaoUserCase.executar(notificacao);
    }
}
