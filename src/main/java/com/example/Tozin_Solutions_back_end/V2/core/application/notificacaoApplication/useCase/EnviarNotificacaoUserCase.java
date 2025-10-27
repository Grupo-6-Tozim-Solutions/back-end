package com.example.Tozin_Solutions_back_end.V2.core.application.notificacaoApplication.useCase;

import com.example.Tozin_Solutions_back_end.V2.core.domain.NotificacaoDomain.Notificacao;
import com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.NotificacaoPersistence.jpa.NotificacaoQueueGateway;
import org.springframework.stereotype.Service;

@Service
public class EnviarNotificacaoUserCase {
    private final NotificacaoQueueGateway queueGateway;

    public EnviarNotificacaoUserCase(NotificacaoQueueGateway queueGateway) {
        this.queueGateway = queueGateway;
    }

    public void executar(Notificacao notificacao) {
        queueGateway.enviarParaFila(notificacao);
    }
}
