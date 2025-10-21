package com.example.Tozin_Solutions_back_end.V2.core.application.MessagingApplication;

import com.example.Tozin_Solutions_back_end.V2.core.domain.MessasingDomain.MensageriaGateway;
import org.springframework.stereotype.Service;

@Service
public class EnviarMensagemUserCase {
    private final MensageriaGateway mensageriaGateway;

    public EnviarMensagemUserCase(MensageriaGateway mensageriaGateway) {
        this.mensageriaGateway = mensageriaGateway;
    }

    public void executar(String mensagem) {
        mensageriaGateway.enviarMensagem(mensagem);
    }
}
