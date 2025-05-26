package com.example.Tozin_Solutions_back_end.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sofa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String modelo;
    private LocalDateTime dataCadastro = LocalDateTime.now();


    private String caminhoImagem;


    public Sofa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }


}
