package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.quantidadePecaEmSofaPersistence.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "quantidade_peca_em_sofa_V2",
        uniqueConstraints = @UniqueConstraint(columnNames = {"sofa_id", "peca_id"}))
public class QuantidadePecaEmSofaJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sofa_id", nullable = false)
    private Long sofaId;

    @Column(name = "peca_id", nullable = false)
    private Long pecaId;

    @Column(name = "quantidade_peca", nullable = false)
    private Double quantidadePeca;

    public QuantidadePecaEmSofaJpaEntity() {}

    public QuantidadePecaEmSofaJpaEntity(Long id, Long sofaId, Long pecaId, Double quantidadePeca) {
        this.id = id;
        this.sofaId = sofaId;
        this.pecaId = pecaId;
        this.quantidadePeca = quantidadePeca;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSofaId() {
        return sofaId;
    }

    public void setSofaId(Long sofaId) {
        this.sofaId = sofaId;
    }

    public Long getPecaId() {
        return pecaId;
    }

    public void setPecaId(Long pecaId) {
        this.pecaId = pecaId;
    }

    public Double getQuantidadePeca() {
        return quantidadePeca;
    }

    public void setQuantidadePeca(Double quantidadePeca) {
        this.quantidadePeca = quantidadePeca;
    }
}

