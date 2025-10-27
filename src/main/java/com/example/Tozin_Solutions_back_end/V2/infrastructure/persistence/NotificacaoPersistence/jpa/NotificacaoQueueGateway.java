package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.NotificacaoPersistence.jpa;

import com.example.Tozin_Solutions_back_end.V2.core.domain.NotificacaoDomain.Notificacao;

public interface NotificacaoQueueGateway {
    void enviarParaFila(Notificacao notificacao);
}
