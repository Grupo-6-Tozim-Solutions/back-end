package com.example.Tozin_Solutions_back_end.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class MovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double quantidadeEntrada;
    private Double quantidadeSaida;
    private LocalDateTime data = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "peca_id")
    private Peca peca;

    public MovimentacaoEstoque(Double quantidadeEntrada, Double quantidadeSaida) {
        this.quantidadeEntrada = quantidadeEntrada;
        this.quantidadeSaida = quantidadeSaida;
    }

    public MovimentacaoEstoque() {
    }

    public Long getId() {
        return id;
    }

    public Double getQuantidadeEntrada() {
        return quantidadeEntrada;
    }

    public void setQuantidadeEntrada(Double quantidadeEntrada) {
        this.quantidadeEntrada = quantidadeEntrada;
    }

    public Double getQuantidadeSaida() {
        return quantidadeSaida;
    }

    public void setQuantidadeSaida(Double quantidadeSaida) {
        this.quantidadeSaida = quantidadeSaida;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }
}
