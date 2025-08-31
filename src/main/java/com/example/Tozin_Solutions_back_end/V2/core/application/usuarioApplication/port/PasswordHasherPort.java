package com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.port;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public interface PasswordHasherPort {
    String hash(String raw);
    boolean matches(String raw, String hash);
}
