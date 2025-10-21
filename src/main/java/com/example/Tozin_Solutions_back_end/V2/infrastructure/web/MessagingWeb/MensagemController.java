package com.example.Tozin_Solutions_back_end.V2.infrastructure.web.MessagingWeb;

import com.example.Tozin_Solutions_back_end.V2.core.application.MessagingApplication.EnviarMensagemUserCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mensagens")
public class MensagemController {
    private final EnviarMensagemUserCase userCase;

    public MensagemController(EnviarMensagemUserCase userCase) {
        this.userCase = userCase;
    }

    @PostMapping
    public String enviar(@RequestBody String texto) {
        userCase.executar(texto);
        return "Mensagem enviada com sucesso!";
    }
}
