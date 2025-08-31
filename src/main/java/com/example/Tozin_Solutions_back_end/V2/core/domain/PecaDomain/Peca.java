package com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain;

import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.NomePeca;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.Quantidade;
import com.example.Tozin_Solutions_back_end.V2.core.domain.PecaDomain.valueObjects.TipoPeca;

import java.time.LocalDateTime;

public class Peca {
    private Long id;
    private NomePeca nome;
    private Quantidade quantidadeEstoque;
    private Quantidade quantidadeMinima;
    private TipoPeca tipo;
    private LocalDateTime dataCadastro;

    private Peca(NomePeca nome, Quantidade quantidadeEstoque, Quantidade quantidadeMinima, TipoPeca tipo) {
        this.nome = nome;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeMinima = quantidadeMinima;
        this.tipo = tipo;
        this.dataCadastro = LocalDateTime.now();
    }

    private Peca(Long id, NomePeca nome, Quantidade quantidadeEstoque, Quantidade quantidadeMinima, TipoPeca tipo, LocalDateTime dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeMinima = quantidadeMinima;
        this.tipo = tipo;
        this.dataCadastro = dataCadastro;
    }

    public static Peca criarPeca(NomePeca nome, Quantidade quantidadeEstoque, Quantidade quantidadeMinima, TipoPeca tipo) {
        return new Peca(nome, quantidadeEstoque, quantidadeMinima, tipo);
    }

    public static Peca existente(Long id, NomePeca nome, Quantidade quantidadeEstoque, Quantidade quantidadeMinima, TipoPeca tipo, LocalDateTime dataCadastro) {
        return new Peca(id, nome, quantidadeEstoque, quantidadeMinima, tipo, dataCadastro);
    }

    public void adicionarEstoque(Quantidade quantidade) {
        this.quantidadeEstoque = this.quantidadeEstoque.adicionar(quantidade);
    }

    public void removerEstoque(Quantidade quantidade) {
        this.quantidadeEstoque = this.quantidadeEstoque.subtrair(quantidade);
    }

    public boolean isEstoqueCritico() {
        return quantidadeEstoque.isCritica(quantidadeMinima);
    }

    public void atualizarNome(NomePeca novoNome) {
        this.nome = novoNome;
    }

    public void atualizarQuantidadeMinima(Quantidade novaQuantidadeMinima) {
        this.quantidadeMinima = novaQuantidadeMinima;
    }

    public void atualizarTipo(TipoPeca novoTipo) {
        this.tipo = novoTipo;
    }

    public Long getId() { return id; }
    public NomePeca getNome() { return nome; }
    public Quantidade getQuantidadeEstoque() { return quantidadeEstoque; }
    public Quantidade getQuantidadeMinima() { return quantidadeMinima; }
    public TipoPeca getTipo() { return tipo; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }

    public Peca comId(Long id) {
        return existente(id, nome, quantidadeEstoque, quantidadeMinima, tipo, dataCadastro);
    }
}

