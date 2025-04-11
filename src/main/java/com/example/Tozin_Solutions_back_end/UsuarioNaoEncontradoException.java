package com.example.Tozin_Solutions_back_end;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public String UsuarioNaoEncontradoException() {
        return "Usuario com id não encontrado";
    }
}
