package com.example.Tozin_Solutions_back_end.dto.sofaDTO;
import jakarta.validation.constraints.NotBlank;

public class CadastrarSofaDTO {
    @NotBlank(message = "Modelo não pode ser vazio!")
    private String modelo;

    @NotBlank(message = "Descrição não pode ser vazia!")
    private String descricao;

    public CadastrarSofaDTO(String modelo, String descricao) {
        this.modelo = modelo;
        this.descricao = descricao;
    }

    public String getModelo() {
        return modelo;
    }

    public String getDescricao() {
        return descricao;
    }
}
