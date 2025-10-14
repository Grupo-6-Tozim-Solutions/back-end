    package com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain;

    import com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain.ValueObjects.CaminhoImagem;
    import com.example.Tozin_Solutions_back_end.V2.core.domain.SofaDomain.ValueObjects.ModeloSofa;

    import java.time.LocalDateTime;

    public class Sofa {
        private Long id;
        private ModeloSofa modelo;
        private CaminhoImagem caminhoImagem;
        private LocalDateTime dataCadastro;

        // Construtor para novo sofá
        private Sofa(ModeloSofa modelo, CaminhoImagem caminhoImagem) {
            this.modelo = modelo;
            this.caminhoImagem = caminhoImagem;
            this.dataCadastro = LocalDateTime.now();
        }

        // Construtor para sofá existente
        private Sofa(Long id, ModeloSofa modelo, CaminhoImagem caminhoImagem, LocalDateTime dataCadastro) {
            this.id = id;
            this.modelo = modelo;
            this.caminhoImagem = caminhoImagem;
            this.dataCadastro = dataCadastro;
        }

        // Factory methods
        public static Sofa criarSofa(ModeloSofa modelo, CaminhoImagem caminhoImagem) {
            return new Sofa(modelo, caminhoImagem);
        }

        public static Sofa existente(Long id, ModeloSofa modelo, CaminhoImagem caminhoImagem, LocalDateTime dataCadastro) {
            return new Sofa(id, modelo, caminhoImagem, dataCadastro);
        }

        // Comportamentos de domínio
        public void atualizarModelo(ModeloSofa novoModelo) {
            this.modelo = novoModelo;
        }

        public void atualizarCaminhoImagem(CaminhoImagem novoCaminhoImagem) {
            this.caminhoImagem = novoCaminhoImagem;
        }

        // Getters
        public Long getId() { return id; }
        public ModeloSofa getModelo() { return modelo; }
        public CaminhoImagem getCaminhoImagem() { return caminhoImagem; }
        public LocalDateTime getDataCadastro() { return dataCadastro; }

        public Sofa comId(Long id) {
            return existente(id, modelo, caminhoImagem, dataCadastro);
        }
    }