package com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.sofaPersistence.jpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sofa_v2")
public class SofaJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String modelo;

    @Column(name = "caminho_imagem", length = 255)
    private String caminhoImagem;

    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    public SofaJpaEntity(Long id, String modelo, String caminhoImagem, LocalDateTime dataCadastro) {
        this.id = id;
        this.modelo = modelo;
        this.caminhoImagem = caminhoImagem;
        this.dataCadastro = dataCadastro;
    }

    public SofaJpaEntity() {}

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

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }
    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

}
