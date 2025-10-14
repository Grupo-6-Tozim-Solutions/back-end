package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.producaoSofaPersistence.jpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "producao_sofa_V2")
public class ProducaoSofaJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sofa_id", nullable = false)
    private Long sofaId;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "data_producao", nullable = false)
    private LocalDateTime dataProducao;

    // Construtores
    public ProducaoSofaJpaEntity() {}

    public ProducaoSofaJpaEntity(Long id, Long sofaId, Integer quantidade, LocalDateTime dataProducao) {
        this.id = id;
        this.sofaId = sofaId;
        this.quantidade = quantidade;
        this.dataProducao = dataProducao;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSofaId() { return sofaId; }
    public void setSofaId(Long sofaId) { this.sofaId = sofaId; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public LocalDateTime getDataProducao() { return dataProducao; }
    public void setDataProducao(LocalDateTime dataProducao) { this.dataProducao = dataProducao; }
}