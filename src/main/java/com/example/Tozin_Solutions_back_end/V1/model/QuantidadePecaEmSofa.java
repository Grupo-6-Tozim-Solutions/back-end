package com.example.Tozin_Solutions_back_end.V1.model;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"idSofa", "idPeca"})
})
public class QuantidadePecaEmSofa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long idSofa;
    private Long idPeca;
    private Double quantidadePeca;

    public QuantidadePecaEmSofa() {
    }

    public Long getId() {
        return id;
    }

    public Long getIdSofa() {
        return idSofa;
    }

    public Long getIdPeca() {
        return idPeca;
    }

    public Double getQuantidadePeca() {
        return quantidadePeca;
    }

    public void setIdSofa(Long idSofa) {
        this.idSofa = idSofa;
    }

    public void setIdPeca(Long idPeca) {
        this.idPeca = idPeca;
    }

    public void setQuantidadePeca(Double quantidadePeca) {
        this.quantidadePeca = quantidadePeca;
    }
}
