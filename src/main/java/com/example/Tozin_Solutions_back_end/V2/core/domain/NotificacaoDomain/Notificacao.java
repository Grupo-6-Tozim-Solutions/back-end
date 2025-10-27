package com.example.Tozin_Solutions_back_end.V2.core.domain.NotificacaoDomain;

public class Notificacao {
    private String destinatario;
    private String Mensagem;

    public Notificacao(String destinatario, String mensagem) {
        this.destinatario = destinatario;
        Mensagem = mensagem;
    }

    public Notificacao() {
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getMensagem() {
        return Mensagem;
    }

    public void setMensagem(String mensagem) {
        Mensagem = mensagem;
    }
}
