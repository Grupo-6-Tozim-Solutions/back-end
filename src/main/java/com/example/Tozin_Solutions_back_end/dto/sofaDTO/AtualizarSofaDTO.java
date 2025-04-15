package com.example.Tozin_Solutions_back_end.dto.sofaDTO;

public class AtualizarSofaDTO {
    private String modelo;

    private String descricao;

    public AtualizarSofaDTO(String modelo, String descricao) {
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
