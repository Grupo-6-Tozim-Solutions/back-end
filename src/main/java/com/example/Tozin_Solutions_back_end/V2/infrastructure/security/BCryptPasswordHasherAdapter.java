package com.example.Tozin_Solutions_back_end.V2.infrastructure.security;

import com.example.Tozin_Solutions_back_end.V2.core.application.usuarioApplication.port.PasswordHasherPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordHasherAdapter implements PasswordHasherPort {

    private final PasswordEncoder passwordEncoder;

    public BCryptPasswordHasherAdapter() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String hash(String senha) {
        return passwordEncoder.encode(senha);
    }

    @Override
    public boolean matches(String senha, String hash) {
        return passwordEncoder.matches(senha, hash);
    }
}