package com.example.Tozin_Solutions_back_end.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class MovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer quantidadeEntrada;
    private Integer quantidadeSaida;
    private LocalDateTime data = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "peca_id")
    private Peca peca;

    public MovimentacaoEstoque(Integer quantidadeEntrada, Integer quantidadeSaida) {
        this.quantidadeEntrada = quantidadeEntrada;
        this.quantidadeSaida = quantidadeSaida;
    }

    public MovimentacaoEstoque() {
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantidadeEntrada() {
        return quantidadeEntrada;
    }

    public void setQuantidadeEntrada(Integer quantidadeEntrada) {
        this.quantidadeEntrada = quantidadeEntrada;
    }

    public Integer getQuantidadeSaida() {
        return quantidadeSaida;
    }

    public void setQuantidadeSaida(Integer quantidadeSaida) {
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
