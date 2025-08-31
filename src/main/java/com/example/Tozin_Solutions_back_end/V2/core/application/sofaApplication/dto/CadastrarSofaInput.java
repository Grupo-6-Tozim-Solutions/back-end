package com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastrarSofaInput(
        @NotBlank(message = "Modelo do sofá é obrigatório")
        @Size(min = 2, max = 50, message = "Modelo deve ter entre 2 e 50 caracteres")
        String modelo,

        String caminhoImagem
) {}
