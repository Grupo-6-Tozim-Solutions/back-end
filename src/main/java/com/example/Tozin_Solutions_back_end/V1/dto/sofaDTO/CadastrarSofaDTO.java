package com.example.Tozin_Solutions_back_end.V1.dto.sofaDTO;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class CadastrarSofaDTO {
    @NotBlank(message = "Modelo não pode ser vazio!")
    private String modelo;

    public CadastrarSofaDTO() {}

    @JsonCreator
    public CadastrarSofaDTO(@JsonProperty("modelo") String modelo) {
        this.modelo = modelo;
    }

    public String getModelo() {
        return modelo;
    }
}
