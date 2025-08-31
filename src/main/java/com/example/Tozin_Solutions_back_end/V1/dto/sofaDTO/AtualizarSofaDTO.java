package com.example.Tozin_Solutions_back_end.dto.sofaDTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AtualizarSofaDTO {
    private String modelo;

    @JsonCreator
    public AtualizarSofaDTO(@JsonProperty("modelo") String modelo) {
        this.modelo = modelo;
    }

    public AtualizarSofaDTO() {}

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}