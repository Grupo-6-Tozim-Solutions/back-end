package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.PecaPersistence.Jpa;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "peca_V2")
public class PecaJpaEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 25)
    private String nome;

    @Column(name = "quantidade_estoque", nullable = false)
    private Double quantidadeEstoque;

    @Column(name = "quantidade_minima", nullable = false)
    private Double quantidadeMinima;

    @Column(nullable = false, length = 20)
    private String tipo;

    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    // Construtores
    public PecaJpaEntity() {
        this.dataCadastro = LocalDateTime.now();
    }

    public PecaJpaEntity(String nome, Double quantidadeEstoque, Double quantidadeMinima, String tipo) {
        this.nome = nome;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeMinima = quantidadeMinima;
        this.tipo = tipo;
        this.dataCadastro = LocalDateTime.now();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Double getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(Double quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }

    public Double getQuantidadeMinima() { return quantidadeMinima; }
    public void setQuantidadeMinima(Double quantidadeMinima) { this.quantidadeMinima = quantidadeMinima; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }

    @PrePersist
    protected void onCreate() {
        if (dataCadastro == null) {
            dataCadastro = LocalDateTime.now();
        }
    }
}

