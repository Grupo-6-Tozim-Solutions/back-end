package com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.port;

public interface TokenProviderPort {
    String generateToken(Long usuarioId, String email, String nome);
    boolean validateToken(String token);
    String getUsernameFromToken(String token);
    Long getUserIdFromToken(String token);
}
