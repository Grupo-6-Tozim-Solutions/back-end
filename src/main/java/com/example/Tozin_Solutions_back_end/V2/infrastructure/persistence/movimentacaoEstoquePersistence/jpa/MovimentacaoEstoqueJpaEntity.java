package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.movimentacaoEstoquePersistence.jpa;

import com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.PecaPersistence.Jpa.PecaJpaEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacao_estoque_V2")
public class MovimentacaoEstoqueJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_movimentacao", nullable = false)
    private LocalDateTime dataMovimentacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "peca_id", nullable = false)
    private PecaJpaEntity peca;

    // ✅ CORREÇÃO: Anotação @Column explícita com nome da coluna
    @Column(name = "quantidade_entrada", nullable = false)
    private Double quantidadeEntrada;

    // ✅ CORREÇÃO: Anotação @Column explícita com nome da coluna
    @Column(name = "quantidade_saida", nullable = false)
    private Double quantidadeSaida;

    // Construtores
    public MovimentacaoEstoqueJpaEntity() {}

    public MovimentacaoEstoqueJpaEntity(Long id, LocalDateTime dataMovimentacao, PecaJpaEntity peca,
                                        Double quantidadeEntrada, Double quantidadeSaida) {
        this.id = id;
        this.dataMovimentacao = dataMovimentacao;
        this.peca = peca;
        this.quantidadeEntrada = quantidadeEntrada;
        this.quantidadeSaida = quantidadeSaida;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDataMovimentacao() { return dataMovimentacao; }
    public void setDataMovimentacao(LocalDateTime dataMovimentacao) { this.dataMovimentacao = dataMovimentacao; }

    public PecaJpaEntity getPeca() { return peca; }
    public void setPeca(PecaJpaEntity peca) { this.peca = peca; }

    public Double getQuantidadeEntrada() { return quantidadeEntrada; }
    public void setQuantidadeEntrada(Double quantidadeEntrada) { this.quantidadeEntrada = quantidadeEntrada; }

    public Double getQuantidadeSaida() { return quantidadeSaida; }
    public void setQuantidadeSaida(Double quantidadeSaida) { this.quantidadeSaida = quantidadeSaida; }
}
