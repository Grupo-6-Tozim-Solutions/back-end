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
    private String descricao;
    private LocalDateTime dataCadastro = LocalDateTime.now();

    @OneToMany
    private List<Peca> pecas = new ArrayList<>();

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public List<Peca> getPecas() {
        return pecas;
    }

    public void adicionarPeca(Peca novaPeca){
        pecas.add(novaPeca);
    }

    public void removerPeca(Peca peca) {
        pecas.remove(peca);
    }
}
