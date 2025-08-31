package com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.port;

public interface PasswordHasher {
    String hash(String raw);
    boolean matches(String raw, String hash);
}
