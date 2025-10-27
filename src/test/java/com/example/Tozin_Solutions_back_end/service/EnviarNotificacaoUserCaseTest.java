package com.example.Tozin_Solutions_back_end.service;

import com.example.Tozin_Solutions_back_end.V2.core.application.notificacaoApplication.useCase.EnviarNotificacaoUserCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.NotificacaoDomain.Notificacao;
import com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.NotificacaoPersistence.jpa.NotificacaoQueueGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class EnviarNotificacaoUserCaseTest {
    private NotificacaoQueueGateway queueGateway;
    private EnviarNotificacaoUserCase useCase;

    @BeforeEach
    void setUp() {
        queueGateway = Mockito.mock(NotificacaoQueueGateway.class);
        useCase = new EnviarNotificacaoUserCase(queueGateway);
    }

    @Test
    void deveChamarMetodoEnviarParaFila() {
        Notificacao notificacao = new Notificacao();
        notificacao.setDestinatario("lucas@teste.com");
        notificacao.setMensagem("Bem-vindo!");

        useCase.executar(notificacao);

        verify(queueGateway, times(1)).enviarParaFila(notificacao);
    }
}
