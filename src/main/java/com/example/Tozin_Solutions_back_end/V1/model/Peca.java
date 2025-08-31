package com.example.Tozin_Solutions_back_end.model;

import com.example.Tozin_Solutions_back_end.enums.TipoPeca;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Peca {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private Double quantidadeEstoque;
    private Double quantidadeMinima;
    private LocalDateTime dataCadastro = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private TipoPeca tipo;

    public Peca(String nome, Double quantidadeEstoque, Double quantidadeMinima) {
        this.nome = nome;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeMinima = quantidadeMinima;
    }

    public Peca() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Double quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Double getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(Double quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public TipoPeca getTipo() {
        return tipo;
    }
    public void setTipo(TipoPeca tipo) {
        this.tipo = tipo;
    }
}
